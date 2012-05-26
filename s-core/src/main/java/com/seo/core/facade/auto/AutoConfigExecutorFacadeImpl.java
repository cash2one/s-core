package com.seo.core.facade.auto;

import com.seo.core.facade.SchedulingFacade;
import com.seo.core.facade.auto.task.RunAutoConfigTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AutoConfigExecutorFacadeImpl implements AutoConfigExecutorFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(AutoConfigExecutorFacadeImpl.class);

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(final Long autoConfigId, Integer times) {
        LOGGER.debug("executing autoconfig: id={} for {} times", autoConfigId, times);

        for (int executeCount = 0; executeCount < times; executeCount++) {
            LOGGER.debug("creating task for autoconfig: id={} for {} time", autoConfigId, executeCount);

            RunAutoConfigTask task = createTask();
            task.setAutoConfigId(autoConfigId);

            schedulingFacade.executeTask(task);
        }
    }

    protected abstract RunAutoConfigTask createTask();
}
