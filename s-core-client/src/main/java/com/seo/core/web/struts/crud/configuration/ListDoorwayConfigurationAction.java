package com.seo.core.web.struts.crud.configuration;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.DoorwayConfigurationFacade;
import com.seo.core.model.DoorwayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListDoorwayConfigurationAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ListDoorwayConfigurationAction.class);

    private List<DoorwayConfiguration> doorwayConfigurations;

    public List<DoorwayConfiguration> getDoorwayConfigurations() {
        return doorwayConfigurations;
    }

    private DoorwayConfigurationFacade doorwayConfigurationFacade;

    public void setDoorwayConfigurationFacade(DoorwayConfigurationFacade doorwayConfigurationFacade) {
        this.doorwayConfigurationFacade = doorwayConfigurationFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("getting doorway configurations list");

        this.doorwayConfigurations = doorwayConfigurationFacade.getAll();

        return SUCCESS;
    }
}
