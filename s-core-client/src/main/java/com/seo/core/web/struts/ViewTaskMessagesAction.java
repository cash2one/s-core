package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.concurrency.dto.TaskStatusDTO;
import com.seo.core.facade.SchedulingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ViewTaskMessagesAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ViewTaskMessagesAction.class);

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    private List<TaskStatusDTO> taskStatuses;
    private TaskStatusDTO taskStatus;

    public List<TaskStatusDTO> getTaskStatuses() {
        return taskStatuses;
    }

    public TaskStatusDTO getTaskStatus() {
        return taskStatus;
    }

    private SchedulingFacade schedulingFacade;

    public void setSchedulingFacade(SchedulingFacade schedulingFacade) {
        this.schedulingFacade = schedulingFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing view task messages action");

        this.taskStatuses = schedulingFacade.getTaskStatuses();
        this.taskStatus = schedulingFacade.getTask(id);

        return SUCCESS;
    }
}
