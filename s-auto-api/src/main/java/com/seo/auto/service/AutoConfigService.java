package com.seo.auto.service;

import com.seo.core.model.AutoConfig;

import java.util.List;

public interface AutoConfigService {
    List<AutoConfig> getAll();
    AutoConfig save(AutoConfig autoConfig);
    void delete(Long id);
}
