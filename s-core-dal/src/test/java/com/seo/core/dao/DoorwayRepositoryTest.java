package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.Doorway;

import javax.inject.Inject;

public class DoorwayRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(DoorwayRepository doorwayRepository) {
        this.repository = (BaseRepository)doorwayRepository;
    }

    @Override
    public BaseModel createModel() {
        return new Doorway();
    }
}
