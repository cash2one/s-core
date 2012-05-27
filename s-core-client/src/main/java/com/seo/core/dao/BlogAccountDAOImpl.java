package com.seo.core.dao;

import com.seo.core.model.account.BlogAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BlogAccountDAOImpl extends HibernateDaoSupport implements BlogAccountDAO{
    private final static Logger LOGGER = LoggerFactory.getLogger(BlogAccountDAOImpl.class);

    @Override
    public void save(BlogAccount blogAccount) {
        getHibernateTemplate().saveOrUpdate(blogAccount);
    }

    @Override
    public void delete(BlogAccount blogAccount) {
        getHibernateTemplate().delete(blogAccount);
    }

    @Override
    public BlogAccount getById(long id) {
        return getHibernateTemplate().load(BlogAccount.class, id);
    }

    @Override
    public List<BlogAccount> getAll() {
        return getHibernateTemplate().loadAll(BlogAccount.class);
    }

    @Override
    public BlogAccount getRandom() {
        List<BlogAccount> blogAccounts = getHibernateTemplate().find("from BlogAccount order by rand()");

        if(blogAccounts.size() < 1) {
            LOGGER.warn("blogAccounts.size() < 1");

            throw new RuntimeException("blogAccounts.size() < 1");
        }

        return blogAccounts.get(0);
    }
}
