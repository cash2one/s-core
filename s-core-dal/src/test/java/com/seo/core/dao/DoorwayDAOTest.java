package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.Doorway;

import javax.inject.Inject;

public class DoorwayDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(DoorwayDAO doorwayDAO) {
        this.repository = (BaseRepository) doorwayDAO;
    }

    @Override
    public BaseModel createModel() {
        return new Doorway();
    }
}
