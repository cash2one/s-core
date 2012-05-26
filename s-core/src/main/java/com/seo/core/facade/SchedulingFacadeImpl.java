package com.seo.core.facade;

import com.seo.core.concurrency.ManagedFuture;
import com.seo.core.concurrency.ManagedFutureState;
import com.seo.core.concurrency.ManagedFutureTask;
import com.seo.core.concurrency.dto.TaskStatusDTO;
import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class SchedulingFacadeImpl implements SchedulingFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(SchedulingFacadeImpl.class);

    private List<ManagedFuture> futures = new ArrayList<ManagedFuture>();

    private AsyncTaskExecutor taskExecutor;

    public void setTaskExecutor(AsyncTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public List<TaskStatusDTO> getTaskStatuses() {
        LOGGER.debug("getting task statuses");

        List<TaskStatusDTO> taskStatusDTOs = new ArrayList<TaskStatusDTO>();
        for (int futureIndex = 0; futureIndex < futures.size(); futureIndex++) {
            ManagedFuture future = futures.get(futureIndex);

            String status = future.getState().toString();
            String name = future.getName();

            List<Message> messages = future.getMessages();
            Message lastMessage = findLastMessage(messages);

            TaskStatusDTO taskStatus = new TaskStatusDTO(futureIndex, name, status, messages, lastMessage);
            taskStatusDTOs.add(taskStatus);
        }

        return taskStatusDTOs;
    }

    private Message findLastMessage(List<Message> messages) {
        Message lastMessage = new Message("no messages");
        if (messages != null && messages.size() > 0) {
            lastMessage = messages.get(messages.size() - 1);
        }
        return lastMessage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void executeTask(AbstractMessageTask task) {
        LOGGER.debug("executing task");

        final ManagedFutureTask futureTask = new ManagedFutureTask(task, null);
        taskExecutor.execute(futureTask);
        futures.add(futureTask);

        LOGGER.debug("adding future to container. new size: " + futures.size());
    }

    @Override
    public void interrupt(int index) {
        LOGGER.debug("cancelling task with index: {}", index);

        ManagedFuture managedFuture = getManagedFuture(index);
        managedFuture.cancel(true);
    }

    private ManagedFuture getManagedFuture(int index) {
        if (index < 0 || index >= futures.size()) {
            throw new RuntimeException("invalid index");
        }

        ManagedFuture managedFuture = futures.get(index);
        return managedFuture;
    }

    @Override
    public void clearTasks() {
        LOGGER.debug("clearing tasks");

        List<ManagedFuture> futuresToDelete = new ArrayList<ManagedFuture>();
        for (ManagedFuture future : futures) {
            //leave only running and waiting tasks
            if (future.getState() == ManagedFutureState.FAILED ||
                    future.getState() == ManagedFutureState.CANCELLED ||
                    future.getState() == ManagedFutureState.COMPLETED) {
                futuresToDelete.add(future);
            }
        }
        futures.removeAll(futuresToDelete);
    }

    @Override
    public TaskStatusDTO getTask(int index) {
        LOGGER.debug("getting task, index={}", index);

        ManagedFuture managedFuture = getManagedFuture(index);

        List<Message> messages = managedFuture.getMessages();
        return new TaskStatusDTO(
                index,
                managedFuture.getName(),
                managedFuture.getState().toString(),
                messages,
                findLastMessage(messages));
    }
}
