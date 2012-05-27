package com.seo.core.facade.command.task;

import com.seo.check.index.IndexCheckerFacade;
import com.seo.check.index.model.IndexCheckResponse;
import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.core.facade.DoorwayFacade;
import com.seo.core.model.Doorway;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CheckIndexTask extends AbstractMessageTask{
    private IndexCheckerFacade indexCheckerFacade;
    private DoorwayFacade doorwayFacade;

    public void setDoorwayFacade(DoorwayFacade doorwayFacade) {
        this.doorwayFacade = doorwayFacade;
    }

    public void setIndexCheckerFacade(IndexCheckerFacade indexCheckerFacade) {
        this.indexCheckerFacade = indexCheckerFacade;
    }

    private Long doorwayId;

    public void setDoorwayId(Long doorwayId) {
        this.doorwayId = doorwayId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        Doorway doorway = doorwayFacade.getById(doorwayId);

        IndexCheckResponse response = indexCheckerFacade.checkIndex(doorway.getUrl());

        doorway.setYandexIndex(response.getResult().get("yandex")); //todo: fix for others
        doorwayFacade.save(doorway);
    }

    private final static String NAME = "check index";

    @Override
    public String toString() {
        return String.format("%s @ id=%d", NAME, doorwayId);
    }
}
