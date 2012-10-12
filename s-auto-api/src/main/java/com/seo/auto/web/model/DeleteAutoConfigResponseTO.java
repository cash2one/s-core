package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

public class DeleteAutoConfigResponseTO extends BaseResponseTO {
    private Long id;

    public DeleteAutoConfigResponseTO(ResponseStatus status, Long id) {
        super(status);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
