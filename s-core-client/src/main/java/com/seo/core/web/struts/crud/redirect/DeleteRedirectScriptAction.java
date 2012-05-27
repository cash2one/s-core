package com.seo.core.web.struts.crud.redirect;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.RedirectScriptFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteRedirectScriptAction extends ActionSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteRedirectScriptAction.class);

    private RedirectScriptFacade redirectScriptFacade;

    public void setRedirectScriptFacade(RedirectScriptFacade redirectScriptFacade) {
        this.redirectScriptFacade = redirectScriptFacade;
    }

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing delete redirect script action, id={}", id);

        redirectScriptFacade.deleteById(id);

        return SUCCESS;
    }
}
