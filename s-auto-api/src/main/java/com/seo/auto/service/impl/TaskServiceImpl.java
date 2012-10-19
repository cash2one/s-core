package com.seo.auto.service.impl;

import com.seo.auto.batch.runner.JobRunner;
import com.seo.auto.service.TaskService;
import com.seo.core.model.AutoConfig;
import com.seo.core.service.AutoConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Inject
    private AutoConfigManager autoConfigManager;

    @Inject
    private JobRunner jobRunner;

    @Override
    public void createTask(Long autoConfigId) {
        AutoConfig autoConfig = autoConfigManager.findById(autoConfigId);

        if(autoConfig == null) {
            logger.error("can't find autoconfig, id=", autoConfigId);

            throw new IllegalArgumentException("can't find autoconfig, id: " + autoConfigId);
        }

        jobRunner.runJob(autoConfig.getConfig());
    }
}
