package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.KeywordGroup;

import javax.inject.Inject;

public class KeywordGroupRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(KeywordGroupRepository keywordGroupRepository) {
        this.repository = (BaseRepository)keywordGroupRepository;
    }

    @Override
    public BaseModel createModel() {
        return new KeywordGroup();
    }
}
