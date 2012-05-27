package com.seo.core.web.struts.crud.template;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.TemplateFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteTemplateAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteTemplateAction.class);

    private TemplateFacade templateFacade;

    public void setTemplateFacade(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing delete template action, id={}", id);

        templateFacade.deleteById(id);

        return SUCCESS;
    }
}
