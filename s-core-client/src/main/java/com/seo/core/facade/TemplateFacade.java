package com.seo.core.facade;

import com.seo.core.facade.request.CreateTemplateRequest;
import com.seo.core.model.Template;

import java.util.List;

public interface TemplateFacade {
    List<Template> getAll();
    Template getById(Long id);
    void save(Template template);
    void deleteById(Long id);
    void createTemplate(CreateTemplateRequest request);
}
