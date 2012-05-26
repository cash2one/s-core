package com.seo.core.dao;

import com.seo.core.model.TextSource;

import java.util.List;

public interface TextSourceDAO {
    TextSource getById(Long id);
    List<TextSource> getAll();
    void save(TextSource textSource);
    void delete(TextSource textSource);
}
