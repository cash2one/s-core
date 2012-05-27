package com.seo.core.web.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.command.DoorwayCommandFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProcessDoorwayCommandAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ProcessDoorwayCommandAction.class);

    private String command;
    private List<Long> ids;

    public void setCommand(String command) {
        this.command = command;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    private DoorwayCommandFacade doorwayCommandFacade;

    public void setDoorwayCommandFacade(DoorwayCommandFacade doorwayCommandFacade) {
        this.doorwayCommandFacade = doorwayCommandFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing process doorway command action, command: {}", command);

        doorwayCommandFacade.processGroup(command, ids);

        return SUCCESS;
    }
}
