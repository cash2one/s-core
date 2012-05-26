package com.seo.core.concurrency.dto;

import com.seo.message.model.Message;

import java.util.List;

public class TaskStatusDTO {
    private Integer id;
    private String name;
    private String status;
    private List<Message> messages;
    private Message lastMessage;

    public TaskStatusDTO(Integer id, String name, String status, List<Message> messages, Message lastMessage) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.messages = messages;
        this.lastMessage = lastMessage;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message getLastMessage() {
        return lastMessage;
    }
}
