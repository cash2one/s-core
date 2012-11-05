package com.seo.auto.service;

import com.seo.auto.web.model.CreateTaskResponseTO;
import com.seo.auto.web.model.TaskStatusResponseTO;
import org.springframework.batch.core.JobExecution;

import java.util.List;

public interface TaskService {
    CreateTaskResponseTO createTask(Long autoConfigId);
    List<String> listTasks();
    TaskStatusResponseTO getTaskStatus(Long taskId);
}
