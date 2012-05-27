package com.seo.core.facade;

import com.seo.core.dao.DoorwayDAO;
import com.seo.core.model.Doorway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DoorwayFacadeImpl implements DoorwayFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(DoorwayFacadeImpl.class);

    private DoorwayDAO doorwayDAO;

    public void setDoorwayDAO(DoorwayDAO doorwayDAO) {
        this.doorwayDAO = doorwayDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Doorway> getAll() {
        List<Doorway> doorways = doorwayDAO.getAll();

        LOGGER.debug("loaded {} doorways", doorways.size());

        return doorways;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Doorway doorway) {
        doorwayDAO.save(doorway);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long doorwayId) {
        Doorway doorway = this.getById(doorwayId);
        doorwayDAO.delete(doorway);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Doorway getById(Long doorwayId) {
        return doorwayDAO.getById(doorwayId);
    }
}
