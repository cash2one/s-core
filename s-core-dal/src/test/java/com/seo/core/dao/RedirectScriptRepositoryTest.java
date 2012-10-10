package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.RedirectScript;

import javax.inject.Inject;

public class RedirectScriptRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(RedirectScriptRepository redirectScriptRepository) {
        this.repository = (BaseRepository) redirectScriptRepository;
    }

    @Override
    public BaseModel createModel() {
        return new RedirectScript();
    }
}
