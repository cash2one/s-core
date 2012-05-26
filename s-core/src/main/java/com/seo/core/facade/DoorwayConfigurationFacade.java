package com.seo.core.facade;

import com.seo.core.model.DoorwayConfiguration;

import java.util.List;

public interface DoorwayConfigurationFacade {
    List<DoorwayConfiguration> getAll();
    void save(DoorwayConfiguration doorwayConfiguration);
    void deleteById(Long id);
    DoorwayConfiguration getById(Long id);
}
