package com.seo.core.web.struts.crud.template;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.model.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTemplateAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateTemplateAction.class);

    private Template template;

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing create template action");

        this.template = new Template();

        return SUCCESS;
    }

    public Template getTemplate() {
        return template;
    }
}
