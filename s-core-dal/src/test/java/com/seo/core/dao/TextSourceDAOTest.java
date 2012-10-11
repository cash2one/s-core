package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.TextSource;

import javax.inject.Inject;

public class TextSourceDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(TextSourceDAO textSourceDAO) {
        this.repository = (BaseRepository) textSourceDAO;
    }

    @Override
    public BaseModel createModel() {
        return new TextSource();
    }
}
