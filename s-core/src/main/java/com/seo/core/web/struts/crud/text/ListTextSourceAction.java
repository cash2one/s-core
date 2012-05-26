package com.seo.core.web.struts.crud.text;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.TextSourceFacade;
import com.seo.core.model.TextSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListTextSourceAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ListTextSourceAction.class);

    private List<TextSource> textSources;

    public List<TextSource> getTextSources() {
        return textSources;
    }

    private TextSourceFacade textSourceFacade;

    public void setTextSourceFacade(TextSourceFacade textSourceFacade) {
        this.textSourceFacade = textSourceFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing list text source action");

        this.textSources = textSourceFacade.getAll();

        return SUCCESS;
    }
}
