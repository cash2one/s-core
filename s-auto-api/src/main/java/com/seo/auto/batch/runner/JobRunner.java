package com.seo.auto.batch.runner;

import com.seo.auto.batch.ContextConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.HashMap;

@Named
public class JobRunner {
    private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);
    private static final String JOB_NAME = "autoConfigJob";
    public static final String TIMESTAMP = "timestamp";

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private JobRegistry jobRegistry;

    public JobExecution runJob(String config) {
        HashMap<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put(ContextConstants.AUTO_CONFIG.getValue(), new JobParameter(config));
        parameters.put(TIMESTAMP, new JobParameter(new Date().getTime()));
        JobParameters jobParameters = new JobParameters(parameters);

        try {
            Job job = jobRegistry.getJob(JOB_NAME);

            JobExecution jobExecution = jobLauncher.run(job, jobParameters);

            logger.info("creating new job: {}" + jobExecution.toString());

            return jobExecution;
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        } catch (NoSuchJobException e) {
            throw new RuntimeException(e);
        }
    }
}
