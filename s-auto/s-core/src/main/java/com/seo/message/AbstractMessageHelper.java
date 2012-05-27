package com.seo.message;

import com.seo.message.model.Message;

public abstract class AbstractMessageHelper implements MessageNotifier{
    private MessageListener messageListener;

    @Override
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    protected void addMessage(String message) {
        messageListener.addMessage(new Message(message));
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }
}
