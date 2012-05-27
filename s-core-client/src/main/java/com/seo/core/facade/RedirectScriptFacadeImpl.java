package com.seo.core.facade;

import com.seo.core.dao.RedirectScriptDAO;
import com.seo.core.model.RedirectScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RedirectScriptFacadeImpl implements RedirectScriptFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(RedirectScriptFacadeImpl.class);

    private RedirectScriptDAO redirectScriptDAO;

    public void setRedirectScriptDAO(RedirectScriptDAO redirectScriptDAO) {
        this.redirectScriptDAO = redirectScriptDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<RedirectScript> getAll() {
        LOGGER.debug("getting all redirect scripts");

        return redirectScriptDAO.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RedirectScript getById(long id) {
        LOGGER.debug("getting redirect script by id: {}", id);

        return redirectScriptDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(RedirectScript redirectScript) {
        LOGGER.debug("saving redirect script, id={}", redirectScript.getId());

        redirectScriptDAO.save(redirectScript);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(long id) {
        LOGGER.debug("deleting redirect sctipt by id: {}", id);

        RedirectScript redirectScript = redirectScriptDAO.getById(id);
        redirectScriptDAO.delete(redirectScript);
    }
}
