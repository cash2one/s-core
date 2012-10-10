package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.Template;

import javax.inject.Inject;

public class TemplateRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(TemplateRepository templateRepository) {
        this.repository = (BaseRepository)templateRepository;
    }

    @Override
    public BaseModel createModel() {
        return new Template();
    }
}
