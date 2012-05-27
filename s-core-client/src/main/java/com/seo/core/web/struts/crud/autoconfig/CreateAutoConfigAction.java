package com.seo.core.web.struts.crud.autoconfig;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.model.AutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAutoConfigAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateAutoConfigAction.class);

    private AutoConfig autoConfig;

    public AutoConfig getAutoConfig() {
        return autoConfig;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing create auto config action");

        this.autoConfig = new AutoConfig();

        return SUCCESS;
    }
}
