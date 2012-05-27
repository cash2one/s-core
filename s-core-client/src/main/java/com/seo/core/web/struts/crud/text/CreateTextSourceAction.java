package com.seo.core.web.struts.crud.text;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.model.TextSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTextSourceAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateTextSourceAction.class);

    private TextSource textSource;

    public TextSource getTextSource() {
        return textSource;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing create text source action");

        this.textSource = new TextSource();

        return SUCCESS;
    }
}
