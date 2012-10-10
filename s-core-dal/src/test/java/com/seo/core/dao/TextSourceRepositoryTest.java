package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.TextSource;

import javax.inject.Inject;

public class TextSourceRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(TextSourceRepository textSourceRepository) {
        this.repository = (BaseRepository)textSourceRepository;
    }

    @Override
    public BaseModel createModel() {
        return new TextSource();
    }
}
