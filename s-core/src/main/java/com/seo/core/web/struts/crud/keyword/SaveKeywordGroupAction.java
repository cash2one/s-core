package com.seo.core.web.struts.crud.keyword;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import com.seo.core.facade.KeywordGroupFacade;
import com.seo.core.facade.request.CreateKeywordGroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveKeywordGroupAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveKeywordGroupAction.class);

    private CreateKeywordGroupRequest request;

    public CreateKeywordGroupRequest getRequest() {
        return request;
    }

    public void setRequest(CreateKeywordGroupRequest request) {
        this.request = request;
    }

    private KeywordGroupFacade keywordGroupFacade;

    public void setKeywordGroupFacade(KeywordGroupFacade keywordGroupFacade) {
        this.keywordGroupFacade = keywordGroupFacade;
    }

    @Override
    @Validations(visitorFields = {
            @VisitorFieldValidator(fieldName = "request", appendPrefix = false)
    })
    public String execute() throws Exception {
        LOGGER.debug("executing save keyword action");

        keywordGroupFacade.create(request);

        return SUCCESS;
    }
}
