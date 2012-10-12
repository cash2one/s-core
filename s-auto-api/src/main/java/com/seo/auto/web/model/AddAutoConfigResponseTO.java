package com.seo.auto.web.model;

import com.seo.auto.web.model.enums.ResponseStatus;

public class AddAutoConfigResponseTO extends BaseResponseTO {
    private Long id;

    public AddAutoConfigResponseTO(ResponseStatus status, Long id) {
        super(status);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
