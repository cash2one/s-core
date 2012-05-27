package com.seo.doorgen.keyword;

import com.seo.doorgen.model.Keyword;
import com.seo.random.facade.RandomFacade;
import com.seo.text.words.AbstractWordProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractKeywordHelper extends AbstractWordProvider{
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractKeywordHelper.class);

    private RandomFacade randomFacade;

    public void setRandomFacade(RandomFacade randomFacade) {
        this.randomFacade = randomFacade;
    }

    protected RandomFacade getRandomFacade() {
        return randomFacade;
    }

    protected Keyword getRandomKeyword(List<Keyword> keywords) {
        if(keywords == null) {
            LOGGER.warn("keywords == null, returning null");

            return null;
        }

        if(keywords.size() == 1) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("keywords.size() == 1, returning first");
            }

            return keywords.get(0);
        }

        Integer keywordIndex = randomFacade.getInteger(keywords.size());
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("returning keyword[ind=" + keywordIndex + "]");
        }

        return keywords.get(keywordIndex);
    }
}
