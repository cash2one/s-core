package com.seo.core.dao;

import com.seo.core.model.DoorwayConfiguration;

import java.util.List;

public interface DoorwayConfigurationDAO{
    List<DoorwayConfiguration> getAll();
    void save(DoorwayConfiguration doorwayConfiguration);
    void delete(DoorwayConfiguration doorwayConfiguration);
    DoorwayConfiguration getById(Long id);
}
