package com.seo.core.web.struts.crud.configuration;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.DoorwayConfigurationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteDoorwayConfigurationAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteDoorwayConfigurationAction.class);

    private DoorwayConfigurationFacade doorwayConfigurationFacade;

    public void setDoorwayConfigurationFacade(DoorwayConfigurationFacade doorwayConfigurationFacade) {
        this.doorwayConfigurationFacade = doorwayConfigurationFacade;
    }

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("deleting doorway configuration, id = {}", id);
        
        doorwayConfigurationFacade.deleteById(id);

        return SUCCESS;
    }
}
