package com.seo.doorgen.keyword;

import com.seo.doorgen.model.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeywordFacadeImpl implements KeywordFacade{
    private List<Keyword> keywords;
    private List<Keyword> usedKeywords = new ArrayList<Keyword>();
    private final static Random RANDOM = new Random();
    private final static Logger LOGGER = LoggerFactory.getLogger(KeywordFacadeImpl.class);

    private final static String KEYWORD_HIGHLIGHT_PATTERN = "<strong>%s</strong>";

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @Override
    public List<Keyword> getRandomKeywords(Integer count) {
        assert keywords != null;

        //if count more than keywords size
        if(keywords.size() < count) {
            //log warning
            LOGGER.warn("keywords list size less than count: " + count + ". returning null");
            //return nothing

            return null;
        }

        //create result list
        List<Keyword> resultKeywords = new ArrayList<Keyword>();
        //while list size less than count
        while(resultKeywords.size() < count) {
            //get random keyword
            Keyword keywordCandidate = getRandomKeyword();

            //if list contains that keyword
            if(resultKeywords.contains(keywordCandidate)) {
                //do nothing
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("skipping already added keyword: " + keywordCandidate.getContent());
                }
            }
            //else
            else {
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("adding keyword to list: " + keywordCandidate.getContent());
                }
                //add to list
                resultKeywords.add(keywordCandidate);
            }
        }

        //return result list
        return resultKeywords;
    }

    private Keyword getRandomKeyword() {
        int keywordIndex = RANDOM.nextInt(keywords.size());

        return keywords.get(keywordIndex);
    }

    @Override
    public List<Keyword> getUnusedKeywords(Integer count) {
        assert keywords != null;
        assert usedKeywords != null;

        //if count more than difference between keywords and used keywords sizes
        if(keywords.size() - usedKeywords.size() < count) {
            //return nothing
            LOGGER.warn("unused keywords list size less than count: " + count + ". returning null");

            return null;
        }

        //create result list
        List<Keyword> resultKeywords = new ArrayList<Keyword>();
        //while result list size lesser than count
        while(resultKeywords.size() < count) {
            //get random word
            Keyword keywordCandidate = getRandomKeyword();
            //if used words contains this word
            if(usedKeywords.contains(keywordCandidate)) {
                //do nothing
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("randomed already used keyword: " + keywordCandidate.getContent() + ". skipping");
                }
            }
            //else
            else {
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("adding randomed keyword to list: " + keywordCandidate.getContent());
                }
                //add to used list
                usedKeywords.add(keywordCandidate);

                //add to result list
                resultKeywords.add(keywordCandidate);

            }
        }
        //return result list
        return resultKeywords;
    }

    @Override
    public Keyword highlightKeyword(Keyword keyword) {
        return new Keyword(
                String.format(KEYWORD_HIGHLIGHT_PATTERN, keyword.getContent())
        );
    }
}
