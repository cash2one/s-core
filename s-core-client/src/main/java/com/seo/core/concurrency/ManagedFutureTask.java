package com.seo.core.concurrency;

import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ManagedFutureTask<V> extends FutureTask<V> implements ManagedFuture<V> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ManagedFutureTask.class);

    private ManagedFutureState state = ManagedFutureState.WAITING;
    private List<Message> messages = new ArrayList<Message>();
    private String name;

    public String getName() {
        return name;
    }

    public ManagedFutureTask(Callable<V> vCallable) {
        super(vCallable);
    }

    public ManagedFutureTask(AbstractMessageTask task, V result) {
        super(task, result);

        this.name = task.toString();
        task.setMessageListener(
                new MessageListener() {
                    @Override
                    public void addMessage(Message message) {
                        messages.add(message);
                    }
                }
        );
    }


    @Override
    protected void done() {
        LOGGER.debug("completed managed future task");
        this.state = ManagedFutureState.COMPLETED;

        super.done();

        try {
            this.get();
        } catch (InterruptedException e) {
            LOGGER.error("interrupted: {}", e.getMessage());
        } catch (ExecutionException e) {
            LOGGER.error("execution exception: {} : {}", e.getMessage(), e.getCause());

            messages.add(new Message("aborted: " + e.getMessage()));

            this.state = ManagedFutureState.FAILED;
        }
    }

    @Override
    public void run() {
        LOGGER.debug("running managed future task");
        this.state = ManagedFutureState.RUNNING;

        try {
            super.run();
        } catch (Throwable t) {
            LOGGER.debug("got exception: {}", t.getMessage());

            this.state = ManagedFutureState.FAILED;
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        LOGGER.debug("cancelling managed future task");
        this.state = ManagedFutureState.CANCELLED;

        return super.cancel(mayInterruptIfRunning);
    }

    @Override
    public ManagedFutureState getState() {
        return state;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }
}
