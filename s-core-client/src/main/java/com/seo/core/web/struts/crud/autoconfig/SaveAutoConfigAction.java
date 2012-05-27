package com.seo.core.web.struts.crud.autoconfig;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.AutoConfigFacade;
import com.seo.core.facade.request.CreateAutoConfigRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveAutoConfigAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveAutoConfigAction.class);

    private CreateAutoConfigRequest request;

    public CreateAutoConfigRequest getRequest() {
        return request;
    }

    public void setRequest(CreateAutoConfigRequest request) {
        this.request = request;
    }

    private AutoConfigFacade autoConfigFacade;

    public void setAutoConfigFacade(AutoConfigFacade autoConfigFacade) {
        this.autoConfigFacade = autoConfigFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing save auto config action");

        this.autoConfigFacade.create(request);

        return SUCCESS;
    }
}
