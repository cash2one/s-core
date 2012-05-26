package com.seo.core.facade;

import com.seo.core.model.Doorway;

import java.util.List;

public interface DoorwayFacade {
    List<Doorway> getAll();
    void save(Doorway doorway);
    void deleteById(Long doorwayId);
    Doorway getById(Long doorwayId);
}
