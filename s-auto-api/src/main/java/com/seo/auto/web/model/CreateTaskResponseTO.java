package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

public class CreateTaskResponseTO extends BaseResponseTO {
    private Long taskId;
    private String taskStatus;
    private boolean running;

    public CreateTaskResponseTO(ResponseStatus status, Long taskId, String taskStatus, boolean running) {
        super(status);

        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.running = running;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
