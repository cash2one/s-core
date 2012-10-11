package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.DoorwayConfiguration;

import javax.inject.Inject;

public class DoorwayConfigurationDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(DoorwayConfigurationDAO doorwayConfigurationDAO) {
        this.repository = (BaseRepository) doorwayConfigurationDAO;
    }

    @Override
    public BaseModel createModel() {
        return new DoorwayConfiguration();
    }
}
