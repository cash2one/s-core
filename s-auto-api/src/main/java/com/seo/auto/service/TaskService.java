package com.seo.auto.service;

import com.seo.auto.web.model.CreateTaskResponseTO;
import com.seo.auto.web.model.TaskStatusResponseTO;
import org.springframework.batch.core.JobExecution;

import java.util.List;

public interface TaskService {
    List<CreateTaskResponseTO> createTask(Long autoConfigId, Long times);
    CreateTaskResponseTO createTask(String config);
    List<TaskStatusResponseTO> listTasks();
    TaskStatusResponseTO getTaskStatus(Long taskId);
}
