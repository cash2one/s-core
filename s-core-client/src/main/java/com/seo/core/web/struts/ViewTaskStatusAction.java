package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.concurrency.dto.TaskStatusDTO;
import com.seo.core.facade.SchedulingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ViewTaskStatusAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ViewTaskStatusAction.class);

    private List<TaskStatusDTO> taskStatuses;

    public List<TaskStatusDTO> getTaskStatuses() {
        return taskStatuses;
    }

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing view task status action");

        this.taskStatuses = schedulingFacade.getTaskStatuses();

        return SUCCESS;
    }
}
