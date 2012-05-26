package com.seo.core.dao;

import com.seo.core.model.TextSource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class TextSourceDAOImpl extends HibernateDaoSupport implements TextSourceDAO{
    @Override
    public TextSource getById(Long id) {
        return getHibernateTemplate().load(TextSource.class, id);
    }

    @Override
    public List<TextSource> getAll() {
        return getHibernateTemplate().loadAll(TextSource.class);
    }

    @Override
    public void save(TextSource textSource) {
        getHibernateTemplate().saveOrUpdate(textSource);
    }

    @Override
    public void delete(TextSource textSource) {
        getHibernateTemplate().delete(textSource);
    }
}
