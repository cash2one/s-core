package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.SchedulingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearTasksAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ClearTasksAction.class);

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing clear tasks action");

        schedulingFacade.clearTasks();

        return SUCCESS;
    }
}
