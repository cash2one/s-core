package com.seo.core.web.struts.crud.text;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.facade.TextSourceFacade;
import com.seo.core.facade.request.CreateTextSourceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveTextSourceAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveTextSourceAction.class);

    private CreateTextSourceRequest request;

    public CreateTextSourceRequest getRequest() {
        return request;
    }

    public void setRequest(CreateTextSourceRequest request) {
        this.request = request;
    }

    private TextSourceFacade textSourceFacade;

    public void setTextSourceFacade(TextSourceFacade textSourceFacade) {
        this.textSourceFacade = textSourceFacade;
    }

    @Override
    @Validations(visitorFields = {
            @VisitorFieldValidator(fieldName = "request", appendPrefix = false)
    })
    public String execute() throws Exception {
        LOGGER.debug("executing save text source action");

        textSourceFacade.create(request);

        return SUCCESS;
    }
}
