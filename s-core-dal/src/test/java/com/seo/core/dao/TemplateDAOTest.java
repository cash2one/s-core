package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.Template;

import javax.inject.Inject;

public class TemplateDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(TemplateDAO templateDAO) {
        this.repository = (BaseRepository) templateDAO;
    }

    @Override
    public BaseModel createModel() {
        return new Template();
    }
}
