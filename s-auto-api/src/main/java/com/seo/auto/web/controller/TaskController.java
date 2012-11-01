package com.seo.auto.web.controller;

import com.seo.auto.service.TaskService;
import com.seo.auto.web.model.CreateTaskRequestTO;
import com.seo.auto.web.model.CreateTaskResponseTO;
import com.seo.auto.web.model.ListTasksResponseTO;
import com.seo.auto.web.model.enums.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

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
    public CreateTaskResponseTO createTask(@RequestBody CreateTaskRequestTO request) {
        return new CreateTaskResponseTO(ResponseStatus.SUCCESS);
    }
}
