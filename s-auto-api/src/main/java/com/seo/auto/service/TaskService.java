package com.seo.auto.service;

import org.springframework.batch.core.JobExecution;

public interface TaskService {
    JobExecution createTask(Long autoConfigId);
}
