package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.SchedulingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptTaskAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(InterruptTaskAction.class);

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing interrupt task action, id={}", id);

        schedulingFacade.interrupt(id);

        return SUCCESS;
    }
}
