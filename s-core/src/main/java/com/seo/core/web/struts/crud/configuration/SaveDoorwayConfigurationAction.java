package com.seo.core.web.struts.crud.configuration;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.facade.DoorwayConfigurationFacade;
import com.seo.core.model.DoorwayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveDoorwayConfigurationAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveDoorwayConfigurationAction.class);

    private DoorwayConfiguration doorwayConfiguration;

    public DoorwayConfiguration getDoorwayConfiguration() {
        return doorwayConfiguration;
    }

    public void setDoorwayConfiguration(DoorwayConfiguration doorwayConfiguration) {
        this.doorwayConfiguration = doorwayConfiguration;
    }

    private DoorwayConfigurationFacade doorwayConfigurationFacade;

    public void setDoorwayConfigurationFacade(DoorwayConfigurationFacade doorwayConfigurationFacade) {
        this.doorwayConfigurationFacade = doorwayConfigurationFacade;
    }

    @Override
    @Validations(visitorFields = {
            @VisitorFieldValidator(fieldName = "doorwayConfiguration", appendPrefix = false)
    })
    public String execute() throws Exception {
        LOGGER.debug("executing save doorway configuration action");

        doorwayConfigurationFacade.save(doorwayConfiguration);

        return SUCCESS;
    }
}
