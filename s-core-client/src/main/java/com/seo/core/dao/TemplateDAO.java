package com.seo.core.dao;

import com.seo.core.model.Template;

import java.util.List;

public interface TemplateDAO {
    List<Template> getAll();
    Template getById(Long id);
    void save(Template template);
    void delete(Template template);
}
