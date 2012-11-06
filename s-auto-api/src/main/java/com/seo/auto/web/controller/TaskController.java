package com.seo.auto.web.controller;

import com.seo.auto.service.TaskService;
import com.seo.auto.web.model.*;
import com.seo.auto.web.model.enums.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/tasks")
public class TaskController {

    @Inject
    private TaskService taskService;

    @RequestMapping(value = "listTasks")
    @ResponseBody
    public ListTasksResponseTO listTasks() {
        return new ListTasksResponseTO(ResponseStatus.SUCCESS, taskService.listTasks());
    }

    @RequestMapping(value = "createTask", method = RequestMethod.POST)
    @ResponseBody
    public List<CreateTaskResponseTO> createTask(@RequestBody CreateTaskRequestTO request) {
        return taskService.createTask(request.getAutoConfigId(), request.getTimes());
    }

    @RequestMapping(value = "runTestTask", method = RequestMethod.POST)
    @ResponseBody
    public CreateTaskResponseTO runTestTask(@RequestBody RunTestTaskRequestTO request) {
        return taskService.createTask(request.getConfig());
    }
}
