package com.seo.core.dao;

import com.seo.core.model.Doorway;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class DoorwayDAOImpl extends HibernateDaoSupport implements DoorwayDAO{
    @Override
    public List<Doorway> getAll() {
        return getHibernateTemplate().loadAll(Doorway.class);
    }

    @Override
    public void save(Doorway doorway) {
        getHibernateTemplate().saveOrUpdate(doorway);
    }

    @Override
    public void delete(Doorway doorway) {
        getHibernateTemplate().delete(doorway);
    }

    @Override
    public Doorway getById(Long doorwayId) {
        return getHibernateTemplate().load(Doorway.class, doorwayId);
    }
}
