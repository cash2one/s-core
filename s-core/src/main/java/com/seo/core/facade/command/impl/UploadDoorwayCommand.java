package com.seo.core.facade.command.impl;

import com.seo.core.facade.SchedulingFacade;
import com.seo.core.facade.command.DoorwayCommand;
import com.seo.core.facade.command.task.UploadDoorwayTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract  class UploadDoorwayCommand implements DoorwayCommand {
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadDoorwayCommand.class);
    private final static String NAME = "upload";

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void process(final Long doorwayId) {
        LOGGER.debug("executing upload doorway command");

        UploadDoorwayTask uploadDoorwayTask = createTask();
        uploadDoorwayTask.setDoorwayId(doorwayId);

        schedulingFacade.executeTask(
                uploadDoorwayTask
        );
    }

    protected abstract UploadDoorwayTask createTask();
}
