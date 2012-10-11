package com.seo.core.dao;

import com.seo.core.model.BaseModel;
import com.seo.core.model.RedirectScript;

import javax.inject.Inject;

public class RedirectScriptDAOTest extends AbstractDaoTest {

    @Inject
    public void setRepository(RedirectScriptDAO redirectScriptDAO) {
        this.repository = (BaseRepository) redirectScriptDAO;
    }

    @Override
    public BaseModel createModel() {
        return new RedirectScript();
    }
}
