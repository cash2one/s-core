package com.seo.core.facade.command.task;

import com.seo.core.facade.DoorwayFacade;
import com.seo.core.facade.PromotionFacade;
import com.seo.core.model.Doorway;
import com.seo.core.model.DoorwayState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PromoteDoorwayTask extends AbstractDoorwayTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(PromoteDoorwayTask.class);

    private DoorwayFacade doorwayFacade;
    private PromotionFacade promotionFacade;

    public void setPromotionFacade(PromotionFacade promotionFacade) {
        this.promotionFacade = promotionFacade;
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

        if (checkDoorwayState(doorway, DoorwayState.UPLOADED)) {
            promotionFacade.promote(doorway);

            doorway.setDoorwayState(DoorwayState.PROMOTED);
            doorwayFacade.save(doorway);

        } else {
            //do nothing
        }
    }

    private final static String NAME = "promote";

    @Override
    public String toString() {
        return String.format("%s @ id=%d", NAME, doorwayId);
    }
}
