package com.seo.core.web.struts.crud.doorway;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.DoorwayFacade;
import com.seo.core.facade.command.DoorwayCommandFacade;
import com.seo.core.model.Doorway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListDoorwayAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ListDoorwayAction.class);

    private List<Doorway> doorways;
    private List<String> commands;

    public List<String> getCommands() {
        return commands;
    }

    public List<Doorway> getDoorways() {
        return doorways;
    }

    private DoorwayFacade doorwayFacade;
    private DoorwayCommandFacade doorwayCommandFacade;

    public void setDoorwayCommandFacade(DoorwayCommandFacade doorwayCommandFacade) {
        this.doorwayCommandFacade = doorwayCommandFacade;
    }

    public void setDoorwayFacade(DoorwayFacade doorwayFacade) {
        this.doorwayFacade = doorwayFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing list doorways action");

        this.doorways = doorwayFacade.getAll();
        this.commands = doorwayCommandFacade.getCommands();

        return SUCCESS;
    }
}
