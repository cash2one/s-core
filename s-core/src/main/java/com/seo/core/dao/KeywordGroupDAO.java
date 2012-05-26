package com.seo.core.dao;

import com.seo.core.model.KeywordGroup;

import java.util.List;

public interface KeywordGroupDAO {
    void save(KeywordGroup keywordGroup);
    List<KeywordGroup> getAll();
    void delete(KeywordGroup keywordGroup);
    KeywordGroup getById(Long id);
}
