package com.seo.core.facade;

import com.seo.core.facade.request.CreateAutoConfigRequest;
import com.seo.core.model.AutoConfig;

import java.util.List;

public interface AutoConfigFacade {
    void deleteById(Long autoConfigId);
    void save(AutoConfig autoConfig);
    List<AutoConfig> getAll();
    AutoConfig getById(Long id);
    void create(CreateAutoConfigRequest request);
}
