package com.seo.core.facade.command.impl;

import com.seo.core.facade.SchedulingFacade;
import com.seo.core.facade.command.DoorwayCommand;
import com.seo.core.facade.command.task.PromoteDoorwayTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PromoteDoorwayCommand implements DoorwayCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(PromoteDoorwayCommand.class);

    private final static String NAME = "promote";

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public void process(Long doorwayId) {
        LOGGER.debug("executing promote doorway command, id = {}", doorwayId);

        PromoteDoorwayTask promoteTask = createTask();
        promoteTask.setDoorwayId(doorwayId);

        schedulingFacade.executeTask(promoteTask);
    }

    @Override
    public String getName() {
        return NAME;
    }

    protected abstract PromoteDoorwayTask createTask();
}
