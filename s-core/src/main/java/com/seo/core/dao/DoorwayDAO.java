package com.seo.core.dao;

import com.seo.core.model.Doorway;

import java.util.List;

public interface DoorwayDAO {
    List<Doorway> getAll();
    void save(Doorway doorway);
    void delete(Doorway doorway);
    Doorway getById(Long doorwayId);
}
