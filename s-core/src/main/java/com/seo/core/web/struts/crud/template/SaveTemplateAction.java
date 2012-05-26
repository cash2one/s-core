package com.seo.core.web.struts.crud.template;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.facade.TemplateFacade;
import com.seo.core.facade.request.CreateTemplateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveTemplateAction extends ActionSupport {
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveTemplateAction.class);

    private TemplateFacade templateFacade;

    public void setTemplateFacade(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    private CreateTemplateRequest request;

    public CreateTemplateRequest getRequest() {
        return request;
    }

    public void setRequest(CreateTemplateRequest request) {
        this.request = request;
    }

    @Override
    @Validations(visitorFields = {
            @VisitorFieldValidator(fieldName = "request", appendPrefix = false)
    })
    public String execute() throws Exception {
        LOGGER.debug("executing save template action");

        templateFacade.createTemplate(request);

        return SUCCESS;
    }
}
