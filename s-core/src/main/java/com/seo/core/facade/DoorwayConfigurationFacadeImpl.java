package com.seo.core.facade;

import com.seo.core.dao.DoorwayConfigurationDAO;
import com.seo.core.model.DoorwayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DoorwayConfigurationFacadeImpl implements DoorwayConfigurationFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(DoorwayConfigurationFacadeImpl.class);

    private DoorwayConfigurationDAO doorwayConfigurationDAO;

    public void setDoorwayConfigurationDAO(DoorwayConfigurationDAO doorwayConfigurationDAO) {
        this.doorwayConfigurationDAO = doorwayConfigurationDAO;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<DoorwayConfiguration> getAll() {
        List<DoorwayConfiguration> doorwayConfigurations = doorwayConfigurationDAO.getAll();

        LOGGER.debug("loading doorway configurations, size: {}", doorwayConfigurations.size());

        return doorwayConfigurations;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(DoorwayConfiguration doorwayConfiguration) {
        LOGGER.debug("saving doorway configuration, id={}", doorwayConfiguration.getId());

        doorwayConfigurationDAO.save(doorwayConfiguration);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        LOGGER.debug("deleting doorway configuration, id={}", id);

        DoorwayConfiguration configuration = doorwayConfigurationDAO.getById(id);
        doorwayConfigurationDAO.delete(configuration);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public DoorwayConfiguration getById(Long id) {
        LOGGER.debug("loading doorway configuration, id={}", id);

        return doorwayConfigurationDAO.getById(id);
    }
}
