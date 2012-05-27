package com.seo.core.facade.command.impl;

import com.seo.core.facade.DoorwayFacade;
import com.seo.core.facade.command.DoorwayCommand;
import com.seo.core.model.Doorway;
import com.seo.core.model.DoorwayState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchivateCommand implements DoorwayCommand{
    private final static Logger LOGGER = LoggerFactory.getLogger(ArchivateCommand.class);

    private final static String NAME = "archivate";

    private DoorwayFacade doorwayFacade;

    public void setDoorwayFacade(DoorwayFacade doorwayFacade) {
        this.doorwayFacade = doorwayFacade;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void process(Long doorwayId) {
        LOGGER.debug("processing doorway, id={}", doorwayId);

        Doorway doorway = doorwayFacade.getById(doorwayId);
        doorway.setDoorwayState(DoorwayState.ARCHIVED);
        doorwayFacade.save(doorway);
    }
}
