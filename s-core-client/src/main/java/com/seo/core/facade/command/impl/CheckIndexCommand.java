package com.seo.core.facade.command.impl;

import com.seo.core.facade.SchedulingFacade;
import com.seo.core.facade.command.DoorwayCommand;
import com.seo.core.facade.command.task.CheckIndexTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CheckIndexCommand implements DoorwayCommand{
    private final static Logger LOGGER = LoggerFactory.getLogger(CheckIndexCommand.class);
    private final static String NAME = "check index";

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void process(Long doorwayId) {
        LOGGER.debug("executing check index command, id={}", doorwayId);

        CheckIndexTask task = createTask();
        task.setDoorwayId(doorwayId);

        schedulingFacade.executeTask(task);
    }

    protected abstract CheckIndexTask createTask();
}
