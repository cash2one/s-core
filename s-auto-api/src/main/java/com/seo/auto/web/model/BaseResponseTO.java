package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

public abstract class BaseResponseTO {
    protected ResponseStatus status;

    protected BaseResponseTO(ResponseStatus status) {
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
