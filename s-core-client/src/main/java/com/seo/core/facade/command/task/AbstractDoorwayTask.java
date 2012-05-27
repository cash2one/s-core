package com.seo.core.facade.command.task;

import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.core.model.Doorway;
import com.seo.core.model.DoorwayState;
import com.seo.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDoorwayTask extends AbstractMessageTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractDoorwayTask.class);

    protected boolean checkDoorwayState(Doorway doorway, DoorwayState state) {
        if (doorway.getDoorwayState().equals(state)) {
            return true;
        } else {
            LOGGER.error("invalid doorway state, skipping: " + doorway.getDoorwayState());

            getMessageListener().addMessage(new Message("invalid doorway state, skipping: " + doorway.getDoorwayState()));
        }

        return false;
    }
}
