package com.seo.auto.service.impl;

import com.seo.auto.batch.runner.JobRunner;
import com.seo.auto.service.TaskService;
import com.seo.core.model.AutoConfig;
import com.seo.core.service.AutoConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
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

    @Override
    public JobExecution createTask(Long autoConfigId) {
        AutoConfig autoConfig = autoConfigManager.findById(autoConfigId);

        if(autoConfig == null) {
            logger.error("can't find autoconfig, id=", autoConfigId);

            throw new IllegalArgumentException("can't find autoconfig, id: " + autoConfigId);
        }

        return jobRunner.runJob(autoConfig.getConfig());
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
}
