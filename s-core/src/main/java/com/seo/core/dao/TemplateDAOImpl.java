package com.seo.core.dao;

import com.seo.core.model.Template;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class TemplateDAOImpl extends HibernateDaoSupport implements TemplateDAO{
    @Override
    public Template getById(Long id) {
        return getHibernateTemplate().load(Template.class, id);
    }

    @Override
    public List<Template> getAll() {
        return getHibernateTemplate().loadAll(Template.class);
    }

    @Override
    public void save(Template template) {
        getHibernateTemplate().saveOrUpdate(template);
    }

    @Override
    public void delete(Template template) {
        getHibernateTemplate().delete(template);
    }
}
