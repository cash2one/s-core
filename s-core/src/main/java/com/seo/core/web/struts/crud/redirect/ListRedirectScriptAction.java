package com.seo.core.web.struts.crud.redirect;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.RedirectScriptFacade;
import com.seo.core.model.RedirectScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListRedirectScriptAction extends ActionSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(ListRedirectScriptAction.class);

    private RedirectScriptFacade redirectScriptFacade;

    public void setRedirectScriptFacade(RedirectScriptFacade redirectScriptFacade) {
        this.redirectScriptFacade = redirectScriptFacade;
    }

    private List<RedirectScript> redirectScripts;

    public List<RedirectScript> getRedirectScripts() {
        return redirectScripts;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing list redirect script action");

        this.redirectScripts = redirectScriptFacade.getAll();

        return SUCCESS;
    }
}
