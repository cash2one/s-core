package com.seo.core.web.struts.crud.keyword;

import com.opensymphony.xwork2.ActionSupport;
import com.seo.core.model.KeywordGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateKeywordGroupAction extends ActionSupport{
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateKeywordGroupAction.class);

    private KeywordGroup keywordGroup;

    public KeywordGroup getKeywordGroup() {
        return keywordGroup;
    }

    @Override
    public String execute() throws Exception {
        LOGGER.debug("executing create keyword group action");

        this.keywordGroup = new KeywordGroup();

        return SUCCESS;
    }
}
