package com.seo.core.facade.doorgen;

import com.seo.core.facade.SchedulingFacade;
import com.seo.core.facade.doorgen.request.CreateDoorwayRequest;
import com.seo.core.facade.doorgen.task.CreateDoorwayTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DoorgenFacadeImpl implements DoorgenFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(DoorgenFacadeImpl.class);

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public void createDoorway(final CreateDoorwayRequest request) {
        LOGGER.debug("processing create doorway request");

        for (int taskIndex = 0; taskIndex < request.getDoorwayCount(); taskIndex++) {
            LOGGER.debug("creating task for index {}", taskIndex);

            CreateDoorwayTask task = createTask();
            task.setRequest(request);
            task.setIndex(taskIndex);

            schedulingFacade.executeTask(task);
        }
    }

    public abstract CreateDoorwayTask createTask();
}
