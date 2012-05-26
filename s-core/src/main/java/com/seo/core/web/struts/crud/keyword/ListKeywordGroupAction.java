package com.seo.core.web.struts.crud.keyword;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.facade.KeywordGroupFacade;
import com.seo.core.model.KeywordGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListKeywordGroupAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(ListKeywordGroupAction.class);

    private List<KeywordGroup> keywordGroups;

    public List<KeywordGroup> getKeywordGroups() {
        return keywordGroups;
    }

    private KeywordGroupFacade keywordGroupFacade;

    public void setKeywordGroupFacade(KeywordGroupFacade keywordGroupFacade) {
        this.keywordGroupFacade = keywordGroupFacade;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing list keyword group action");

        this.keywordGroups = keywordGroupFacade.getAll();

        return SUCCESS;
    }
}
