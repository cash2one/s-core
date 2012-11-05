package com.seo.auto.service.impl;

import com.seo.auto.batch.runner.JobRunner;
import com.seo.auto.service.TaskService;
import com.seo.auto.web.model.CreateTaskResponseTO;
import com.seo.auto.web.model.TaskStatusResponseTO;
import com.seo.auto.web.model.enums.ResponseStatus;
import com.seo.core.model.AutoConfig;
import com.seo.core.service.AutoConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Inject
    private AutoConfigManager autoConfigManager;

    @Inject
    private JobRunner jobRunner;

    @Inject
    private JobOperator jobOperator;

    @Inject
    private JobExplorer jobExplorer;

    @Override
    public List<CreateTaskResponseTO> createTask(Long autoConfigId, Long times) {
        AutoConfig autoConfig = autoConfigManager.findById(autoConfigId);

        if(autoConfig == null) {
            logger.error("can't find autoconfig, id=", autoConfigId);

            throw new IllegalArgumentException("can't find autoconfig, id: " + autoConfigId);
        }

        List<CreateTaskResponseTO> responses = new ArrayList<CreateTaskResponseTO>();
        for(int i=0; i<times; i++) {
            JobExecution jobExecution = jobRunner.runJob(autoConfig.getConfig());
            CreateTaskResponseTO response = new CreateTaskResponseTO(ResponseStatus.SUCCESS, jobExecution.getJobId(), jobExecution.getStatus().toString(), jobExecution.isRunning());

            responses.add(response);
        }

        return responses;
    }

    @Override
    public List<String> listTasks() {
        Set<String> jobNames = jobOperator.getJobNames();

        List<String> jobs = new ArrayList<String>();
        for (String jobName : jobNames) {
            try {
                Set<Long> ids = jobOperator.getRunningExecutions(jobName);

                for (Long id : ids) {
                    jobs.add(jobName + id);
                }
            } catch (NoSuchJobException e) {
                logger.error("no such job: ", e);
            }
        }

        return jobs;
    }

    @Override
    public TaskStatusResponseTO getTaskStatus(Long taskId) {
        JobExecution jobExecution = jobExplorer.getJobExecution(taskId);

        return new TaskStatusResponseTO(jobExecution.getJobId(), jobExecution.getStatus().toString(), jobExecution.isRunning());
    }
}
