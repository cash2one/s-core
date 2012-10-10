package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.DoorwayConfiguration;

import javax.inject.Inject;

public class DoorwayConfigurationRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(DoorwayConfigurationRepository doorwayConfigurationRepository) {
        this.repository = (BaseRepository)doorwayConfigurationRepository;
    }

    @Override
    public BaseModel createModel() {
        return new DoorwayConfiguration();
    }
}
