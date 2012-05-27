package com.seo.doorgen.keyword;

import com.seo.doorgen.model.Keyword;

import java.util.List;

public interface KeywordFacade {
    List<Keyword> getRandomKeywords(Integer count);
    List<Keyword> getUnusedKeywords(Integer count);

    /**
     * Highlights provided keyword with html tags like <b> <strong>
     * @param keyword keyword to highlight
     * @return highlight result
     */
    Keyword highlightKeyword(Keyword keyword);
}
