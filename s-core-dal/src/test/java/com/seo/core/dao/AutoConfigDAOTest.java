package com.seo.core.dao;

import com.seo.core.model.AutoConfig;
import com.seo.core.model.BaseModel;

import javax.inject.Inject;

public class AutoConfigDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(AutoConfigDAO autoConfigDAO) {
        repository = (BaseRepository) autoConfigDAO;
    }

    @Override
    public BaseModel createModel() {
        return new AutoConfig(null, "test auto config", "config");
    }
}
