package com.seo.core.facade;

import com.seo.core.facade.request.CreateTextSourceRequest;
import com.seo.core.model.TextSource;

import java.util.List;

public interface TextSourceFacade {
    TextSource getById(Long id);
    List<TextSource> getAll();
    void save(TextSource textSource);
    void deleteById(Long id);
    void create(CreateTextSourceRequest request);
}
