package com.seo.core.dao;

import com.seo.core.model.AutoConfig;
import com.seo.core.model.BaseModel;

import javax.inject.Inject;

public class AutoConfigRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(AutoConfigRepository autoConfigRepository) {
        repository = (BaseRepository) autoConfigRepository;
    }

    @Override
    public BaseModel createModel() {
        return new AutoConfig(null, "test auto config", "config");
    }
}
