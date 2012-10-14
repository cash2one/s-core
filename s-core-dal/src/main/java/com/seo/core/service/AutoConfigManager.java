package com.seo.core.service;

import com.seo.core.model.AutoConfig;

import java.util.List;

public interface AutoConfigManager {
    List<AutoConfig> getAll();
    AutoConfig save(AutoConfig autoConfig);
    AutoConfig findById(Long id);
}
