package com.seo.core.web.struts.crud.configuration;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.DoorwayConfigurationFacade;
import com.seo.core.model.DoorwayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditDoorwayConfigurationAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(EditDoorwayConfigurationAction.class);
    
    private DoorwayConfigurationFacade doorwayConfigurationFacade;

    public void setDoorwayConfigurationFacade(DoorwayConfigurationFacade doorwayConfigurationFacade) {
        this.doorwayConfigurationFacade = doorwayConfigurationFacade;
    }

    private Long id;
    private DoorwayConfiguration doorwayConfiguration;

    public void setId(Long id) {
        this.id = id;
    }

    public DoorwayConfiguration getDoorwayConfiguration() {
        return doorwayConfiguration;
    }

    public void setDoorwayConfiguration(DoorwayConfiguration doorwayConfiguration) {
        this.doorwayConfiguration = doorwayConfiguration;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("editing doorway configuration, id={}", + id);

        this.doorwayConfiguration = doorwayConfigurationFacade.getById(id);

        return SUCCESS;
    }
}
