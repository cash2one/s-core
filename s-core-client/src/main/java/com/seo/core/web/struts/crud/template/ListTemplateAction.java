package com.seo.core.web.struts.crud.template;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.TemplateFacade;
import com.seo.core.model.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListTemplateAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ListTemplateAction.class);

    private List<Template> templates;

    private TemplateFacade templateFacade;

    public void setTemplateFacade(TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing list template action");

        this.templates = templateFacade.getAll();

        return SUCCESS;
    }

    public List<Template> getTemplates() {
        return templates;
    }
}
