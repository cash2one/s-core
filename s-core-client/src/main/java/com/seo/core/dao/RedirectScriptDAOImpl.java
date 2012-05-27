package com.seo.core.dao;

import com.seo.core.model.RedirectScript;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;


public class RedirectScriptDAOImpl extends HibernateDaoSupport implements RedirectScriptDAO{
    @Override
    public List<RedirectScript> getAll() {
        return getHibernateTemplate().loadAll(RedirectScript.class);
    }

    @Override
    public void save(RedirectScript redirectScript) {
        getHibernateTemplate().saveOrUpdate(redirectScript);
    }

    @Override
    public void delete(RedirectScript redirectScript) {
        getHibernateTemplate().delete(redirectScript);
    }

    @Override
    public RedirectScript getById(long id) {
        return getHibernateTemplate().load(RedirectScript.class, id);
    }
}
