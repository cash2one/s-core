package com.seo.core.dao;

import com.seo.core.model.DoorwayConfiguration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class DoorwayConfigurationDAOImpl extends HibernateDaoSupport implements DoorwayConfigurationDAO{
    @Override
    public List<DoorwayConfiguration> getAll() {
        return getHibernateTemplate().loadAll(DoorwayConfiguration.class);
    }

    @Override
    public void save(DoorwayConfiguration doorwayConfiguration) {
        getHibernateTemplate().saveOrUpdate(doorwayConfiguration);
    }

    @Override
    public void delete(DoorwayConfiguration doorwayConfiguration) {
        getHibernateTemplate().delete(doorwayConfiguration);
    }

    @Override
    public DoorwayConfiguration getById(Long id) {
        return getHibernateTemplate().load(DoorwayConfiguration.class, id);
    }
}
