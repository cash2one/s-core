package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

import java.util.List;

public class ListTasksResponseTO extends BaseResponseTO {
    private List<String> tasks;

    public ListTasksResponseTO(ResponseStatus status, List<String> tasks) {
        super(status);
        this.tasks = tasks;
    }

    public List<String> getTasks() {
        return tasks;
    }
}
