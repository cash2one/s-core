package com.seo.core.dao;

import com.seo.core.model.AutoConfig;

import java.util.List;

public interface AutoConfigDAO {
    void delete(AutoConfig autoConfig);
    void save(AutoConfig autoConfig);
    List<AutoConfig> getAll();
    AutoConfig getById(Long id);
}
