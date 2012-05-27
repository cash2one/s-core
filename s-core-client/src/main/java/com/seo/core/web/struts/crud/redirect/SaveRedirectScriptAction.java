package com.seo.core.web.struts.crud.redirect;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.facade.RedirectScriptFacade;
import com.seo.core.model.RedirectScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveRedirectScriptAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveRedirectScriptAction.class);

    private RedirectScriptFacade redirectScriptFacade;

    public void setRedirectScriptFacade(RedirectScriptFacade redirectScriptFacade) {
        this.redirectScriptFacade = redirectScriptFacade;
    }

    private RedirectScript redirectScript;

    public RedirectScript getRedirectScript() {
        return redirectScript;
    }

    public void setRedirectScript(RedirectScript redirectScript) {
        this.redirectScript = redirectScript;
    }

    @Override
    @Validations(visitorFields = {
            @VisitorFieldValidator(fieldName = "redirectScript", appendPrefix = false)
    })
    public String execute() throws Exception {
        LOGGER.debug("executing save redirect script action");

        redirectScriptFacade.save(redirectScript);

        return SUCCESS;
    }
}
