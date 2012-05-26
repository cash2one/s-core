package com.seo.core.facade;

import com.seo.core.model.RedirectScript;

import java.util.List;

public interface RedirectScriptFacade {
    List<RedirectScript> getAll();
    RedirectScript getById(long id);
    void save(RedirectScript redirectScript);
    void deleteById(long id);
}
