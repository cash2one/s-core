package com.seo.core.facade;

import com.seo.core.concurrency.dto.TaskStatusDTO;
import com.seo.core.concurrency.task.AbstractMessageTask;

import java.util.List;

public interface SchedulingFacade {
    List<TaskStatusDTO> getTaskStatuses();
    void executeTask(AbstractMessageTask task);
    void interrupt(int index);
    void clearTasks();
    TaskStatusDTO getTask(int index);
}
