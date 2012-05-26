package com.seo.core.dao;

import com.seo.core.model.RedirectScript;

import java.util.List;

public interface RedirectScriptDAO {
    List<RedirectScript> getAll();
    void save(RedirectScript redirectScript);
    void delete(RedirectScript redirectScript);
    RedirectScript getById(long id);
}
