package com.seo.core.concurrency;

import com.seo.message.model.Message;

import java.util.List;
import java.util.concurrent.Future;

public interface ManagedFuture<V> extends Future<V> {
    ManagedFutureState getState();
    List<Message> getMessages();
    String getName();
}
