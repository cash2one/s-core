package com.seo.core.web.struts.crud.autoconfig;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.AutoConfigFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteAutoConfigAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteAutoConfigAction.class);

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    private AutoConfigFacade autoConfigFacade;

    public void setAutoConfigFacade(AutoConfigFacade autoConfigFacade) {
        this.autoConfigFacade = autoConfigFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executin delete auto config action, id = {}", id);

        autoConfigFacade.deleteById(id);

        return SUCCESS;
    }
}
