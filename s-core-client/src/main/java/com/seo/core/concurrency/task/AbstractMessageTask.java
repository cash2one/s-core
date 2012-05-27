package com.seo.core.concurrency.task;

import com.seo.message.MessageListener;
import com.seo.message.MessageNotifier;
import com.seo.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMessageTask implements Runnable, MessageNotifier {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractMessageTask.class);

    private MessageListener messageListener;

    @Override
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    protected void addMessage(Message message) {
        if (messageListener != null) {
            messageListener.addMessage(message);
        } else {
            LOGGER.debug("no instance of message listener to add message: {}", message.getContent());
        }
    }

    public abstract String toString();
}
