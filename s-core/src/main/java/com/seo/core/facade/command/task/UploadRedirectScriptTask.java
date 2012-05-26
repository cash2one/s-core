package com.seo.core.facade.command.task;

import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.core.facade.DoorwayFacade;
import com.seo.core.facade.upload.UploadFacade;
import com.seo.core.model.Doorway;
import com.seo.core.model.DoorwayState;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UploadRedirectScriptTask extends AbstractMessageTask {
    private DoorwayFacade doorwayFacade;
    private UploadFacade uploadFacade;

    public void setUploadFacade(UploadFacade uploadFacade) {
        this.uploadFacade = uploadFacade;
    }

    public void setDoorwayFacade(DoorwayFacade doorwayFacade) {
        this.doorwayFacade = doorwayFacade;
    }

    private Long doorwayId;

    public void setDoorwayId(Long doorwayId) {
        this.doorwayId = doorwayId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        Doorway doorway = doorwayFacade.getById(doorwayId);

        uploadFacade.uploadRedirectScript(doorway);

        doorway.setDoorwayState(DoorwayState.REDIRECTED);
        doorwayFacade.save(doorway);

    }

    private final static String NAME = "upload redirect";

    @Override
    public String toString() {
        return String.format("%s @ id=%d", NAME, doorwayId);
    }
}
