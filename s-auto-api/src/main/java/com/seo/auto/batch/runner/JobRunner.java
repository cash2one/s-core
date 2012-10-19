package com.seo.auto.batch.runner;

import com.seo.auto.batch.ContextConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;

@Named
public class JobRunner {
    private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job job;

    public void runJob(String config) {
        HashMap<String, JobParameter> parameters = new HashMap<String, JobParameter>();
        parameters.put(ContextConstants.AUTO_CONFIG.getValue(), new JobParameter(config));
        JobParameters jobParameters = new JobParameters(parameters);

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
