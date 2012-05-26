package com.seo.core.web.struts.crud.redirect;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.RedirectScriptFacade;
import com.seo.core.model.RedirectScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadRedirectScriptAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(LoadRedirectScriptAction.class);

    private RedirectScriptFacade redirectScriptFacade;

    public void setRedirectScriptFacade(RedirectScriptFacade redirectScriptFacade) {
        this.redirectScriptFacade = redirectScriptFacade;
    }

    private Long id;
    private RedirectScript redirectScript;

    public void setId(Long id) {
        this.id = id;
    }

    public RedirectScript getRedirectScript() {
        return redirectScript;
    }

    public void setRedirectScript(RedirectScript redirectScript) {
        this.redirectScript = redirectScript;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing load redirect script action, id={}", id);

        this.redirectScript = redirectScriptFacade.getById(id);

        return SUCCESS;
    }
}
