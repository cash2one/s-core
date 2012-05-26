package com.seo.doorgen.component.naming;

import com.seo.doorgen.keyword.AbstractKeywordHelper;
import com.seo.doorgen.model.Keyword;

import java.util.List;


public class RandomKeywordPageNamingStrategy extends AbstractKeywordHelper implements PageNamingStrategy{
    @Override
    public String generatePageName(List<Keyword> keywords) {
        Keyword keyword = getRandomKeyword(keywords);

        return toTranslit(keyword.getContent());
    }
}
