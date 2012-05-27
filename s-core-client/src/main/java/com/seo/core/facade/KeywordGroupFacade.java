package com.seo.core.facade;

import com.seo.core.facade.request.CreateKeywordGroupRequest;
import com.seo.core.model.KeywordGroup;

import java.util.List;

public interface KeywordGroupFacade {
    void save(KeywordGroup keywordGroup);
    List<KeywordGroup> getAll();
    void create(CreateKeywordGroupRequest request);
    void delete(Long id);
    KeywordGroup getById(Long id);
}
