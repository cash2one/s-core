package com.seo.core.web.struts.crud.configuration;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.model.DoorwayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDoorwayConfigurationAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateDoorwayConfigurationAction.class);

    private DoorwayConfiguration doorwayConfiguration;

    public DoorwayConfiguration getDoorwayConfiguration() {
        return doorwayConfiguration;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing create doorway configuration action");

        this.doorwayConfiguration = new DoorwayConfiguration();

        return SUCCESS;
    }
}
