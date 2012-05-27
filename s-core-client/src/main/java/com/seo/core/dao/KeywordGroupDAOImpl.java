package com.seo.core.dao;

import com.seo.core.model.KeywordGroup;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class KeywordGroupDAOImpl extends HibernateDaoSupport implements KeywordGroupDAO{
    @Override
    public void save(KeywordGroup keywordGroup) {
        getHibernateTemplate().saveOrUpdate(keywordGroup);
    }

    @Override
    public List<KeywordGroup> getAll() {
        return getHibernateTemplate().loadAll(KeywordGroup.class);
    }

    @Override
    public void delete(KeywordGroup keywordGroup) {
        getHibernateTemplate().delete(keywordGroup);
    }

    @Override
    public KeywordGroup getById(Long id) {
        return getHibernateTemplate().load(KeywordGroup.class, id);
    }
}
