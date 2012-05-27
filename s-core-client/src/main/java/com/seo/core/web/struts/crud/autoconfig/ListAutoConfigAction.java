package com.seo.core.web.struts.crud.autoconfig;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.AutoConfigFacade;
import com.seo.core.model.AutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListAutoConfigAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ListAutoConfigAction.class);

    private AutoConfigFacade autoConfigFacade;

    public void setAutoConfigFacade(AutoConfigFacade autoConfigFacade) {
        this.autoConfigFacade = autoConfigFacade;
    }

    private List<AutoConfig> autoConfigs;

    public List<AutoConfig> getAutoConfigs() {
        return autoConfigs;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing list autoconfigs action");

        this.autoConfigs = autoConfigFacade.getAll();

        return SUCCESS;
    }
}
