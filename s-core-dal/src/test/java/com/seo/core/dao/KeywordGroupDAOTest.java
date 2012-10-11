package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.KeywordGroup;

import javax.inject.Inject;

public class KeywordGroupDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(KeywordGroupDAO keywordGroupDAO) {
        this.repository = (BaseRepository) keywordGroupDAO;
    }

    @Override
    public BaseModel createModel() {
        return new KeywordGroup();
    }
}
