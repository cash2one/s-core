package com.seo.core.web.struts.doorgen;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.facade.doorgen.DoorgenFacade;
import com.seo.core.facade.doorgen.request.CreateDoorwayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDoorwayAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateDoorwayAction.class);

    private CreateDoorwayRequest request;

    public CreateDoorwayRequest getRequest() {
        return request;
    }

    public void setRequest(CreateDoorwayRequest request) {
        this.request = request;
    }

    private DoorgenFacade doorgenFacade;

    public void setDoorgenFacade(DoorgenFacade doorgenFacade) {
        this.doorgenFacade = doorgenFacade;
    }

    @Override
    @Validations(visitorFields = {
            @VisitorFieldValidator(fieldName = "request", appendPrefix = false)
    })
    public String execute() throws Exception {
        LOGGER.debug("executing create doorway action");

        doorgenFacade.createDoorway(request);

        return SUCCESS;
    }
}
