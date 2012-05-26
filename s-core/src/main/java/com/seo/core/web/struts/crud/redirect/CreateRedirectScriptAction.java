package com.seo.core.web.struts.crud.redirect;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.model.RedirectScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateRedirectScriptAction extends ActionSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateRedirectScriptAction.class);

    private RedirectScript redirectScript;

    public RedirectScript getRedirectScript() {
        return redirectScript;
    }

    public void setRedirectScript(RedirectScript redirectScript) {
        this.redirectScript = redirectScript;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing create redirect script action");

        this.redirectScript = new RedirectScript();

        return SUCCESS;
    }
}
