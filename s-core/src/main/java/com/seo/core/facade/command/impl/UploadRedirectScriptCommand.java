package com.seo.core.facade.command.impl;

import com.seo.core.facade.SchedulingFacade;
import com.seo.core.facade.command.DoorwayCommand;
import com.seo.core.facade.command.task.UploadRedirectScriptTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UploadRedirectScriptCommand implements DoorwayCommand{
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadRedirectScriptCommand.class);

    private final static String NAME = "upload redirect script";

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
        LOGGER.debug("executing upload redirect script command");

        UploadRedirectScriptTask uploadTask = createTask();
        uploadTask.setDoorwayId(doorwayId);

        schedulingFacade.executeTask(uploadTask);
    }

    protected abstract UploadRedirectScriptTask createTask();
}
