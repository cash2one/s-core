package com.seo.core.dao;

import com.seo.core.model.AutoConfig;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class AutoConfigDAOImpl extends HibernateDaoSupport implements AutoConfigDAO{
    @Override
    public void delete(AutoConfig autoConfig) {
        getHibernateTemplate().delete(autoConfig);
    }

    @Override
    public void save(AutoConfig autoConfig) {
        getHibernateTemplate().saveOrUpdate(autoConfig);
    }

    @Override
    public List<AutoConfig> getAll() {
        return getHibernateTemplate().loadAll(AutoConfig.class);
    }

    @Override
    public AutoConfig getById(Long id) {
        return getHibernateTemplate().load(AutoConfig.class, id);
    }
}
