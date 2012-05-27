package com.seo.core.web.struts.crud.keyword;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.KeywordGroupFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteKeywordGroupAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteKeywordGroupAction.class);

    private KeywordGroupFacade keywordGroupFacade;

    public void setKeywordGroupFacade(KeywordGroupFacade keywordGroupFacade) {
        this.keywordGroupFacade = keywordGroupFacade;
    }

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing delete keyword group action, id={}", id);

        this.keywordGroupFacade.delete(id);

        return SUCCESS;
    }
}
