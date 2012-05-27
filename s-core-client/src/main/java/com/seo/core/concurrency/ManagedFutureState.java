package com.seo.core.concurrency;

public enum ManagedFutureState {
    WAITING,
    RUNNING,
    COMPLETED,
    CANCELLED,
    FAILED
}
