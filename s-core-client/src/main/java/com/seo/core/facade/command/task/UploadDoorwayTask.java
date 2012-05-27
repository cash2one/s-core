package com.seo.core.facade.command.task;

import com.seo.core.facade.DoorwayFacade;
import com.seo.core.facade.upload.UploadFacade;
import com.seo.core.model.Doorway;
import com.seo.core.model.DoorwayState;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UploadDoorwayTask extends AbstractDoorwayTask {
    private UploadFacade uploadFacade;
    private DoorwayFacade doorwayFacade;

    public void setDoorwayFacade(DoorwayFacade doorwayFacade) {
        this.doorwayFacade = doorwayFacade;
    }

    public void setUploadFacade(UploadFacade uploadFacade) {
        this.uploadFacade = uploadFacade;
    }

    private Long doorwayId;

    public void setDoorwayId(Long doorwayId) {
        this.doorwayId = doorwayId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        Doorway doorway = doorwayFacade.getById(doorwayId);

        if (checkDoorwayState(doorway, DoorwayState.NEW)) {
            uploadFacade.setMessageListener(getMessageListener());
            uploadFacade.uploadDoorway(doorway);

            doorway.setDoorwayState(DoorwayState.UPLOADED);
            doorwayFacade.save(doorway);
        } else {
            //do nothing
        }
    }

    private final static String NAME = "upload";

    @Override
    public String toString() {
        return String.format("%s @ id=%d", NAME, doorwayId);
    }
}
