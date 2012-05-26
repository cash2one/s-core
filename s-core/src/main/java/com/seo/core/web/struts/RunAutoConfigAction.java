package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.auto.AutoConfigExecutorFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunAutoConfigAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(RunAutoConfigAction.class);

    private AutoConfigExecutorFacade autoConfigExecutorFacade;

    public void setAutoConfigExecutorFacade(AutoConfigExecutorFacade autoConfigExecutorFacade) {
        this.autoConfigExecutorFacade = autoConfigExecutorFacade;
    }

    private Long autoConfigId;

    public void setAutoConfigId(Long autoConfigId) {
        this.autoConfigId = autoConfigId;
    }

    private Integer times;

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing run auto config action");

        autoConfigExecutorFacade.execute(autoConfigId, times);

        return SUCCESS;
    }
}
