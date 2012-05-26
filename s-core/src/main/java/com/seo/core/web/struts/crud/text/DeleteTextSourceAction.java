package com.seo.core.web.struts.crud.text;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.TextSourceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteTextSourceAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteTextSourceAction.class);

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    private TextSourceFacade textSourceFacade;

    public void setTextSourceFacade(TextSourceFacade textSourceFacade) {
        this.textSourceFacade = textSourceFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing delete text source action, id = {}", id);

        textSourceFacade.deleteById(id);

        return SUCCESS;
    }
}
