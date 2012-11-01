package com.seo.auto.service;

import org.springframework.batch.core.JobExecution;

import java.util.List;

public interface TaskService {
    JobExecution createTask(Long autoConfigId);
    List<String> listTasks();
}
