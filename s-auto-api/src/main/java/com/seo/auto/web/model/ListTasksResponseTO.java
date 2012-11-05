package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

import java.util.List;

public class ListTasksResponseTO extends BaseResponseTO {
    private List<TaskStatusResponseTO> tasks;

    public ListTasksResponseTO(ResponseStatus status, List<TaskStatusResponseTO> tasks) {
        super(status);
        this.tasks = tasks;
    }

    public List<TaskStatusResponseTO> getTasks() {
        return tasks;
    }
}
