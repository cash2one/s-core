package com.seo.core.dao;

import com.seo.core.model.account.EmailAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class EmailAccountDAOImpl extends HibernateDaoSupport implements EmailAccountDAO{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailAccountDAOImpl.class);

    @Override
    public void save(EmailAccount emailAccount) {
        getHibernateTemplate().saveOrUpdate(emailAccount);
    }

    @Override
    public void delete(EmailAccount emailAccount) {
        getHibernateTemplate().delete(emailAccount);
    }

    @Override
    public EmailAccount getById(long emailAccountId) {
        return getHibernateTemplate().load(EmailAccount.class, emailAccountId);
    }

    @Override
    public List<EmailAccount> getAll() {
        return getHibernateTemplate().loadAll(EmailAccount.class);
    }

    @Override
    public EmailAccount getRandom() {
        List<EmailAccount> emailAccounts = getHibernateTemplate().find("from EmailAccount order by rand()");

        if(emailAccounts.size() < 1) {
            LOGGER.warn("emailAccounts.size() < 1: {}", emailAccounts.size());

            return null;
        }

        return emailAccounts.get(0);
    }

    @Override
    public EmailAccount getRandomByHost(String type) {
        List<EmailAccount> emailAccounts = getHibernateTemplate().find("from EmailAccount where host = ? order by rand()", type); 

        if(emailAccounts.size() < 1) {
            LOGGER.warn("emailAccounts.size() < 1: {}", emailAccounts.size());

            return null;
        }

        return emailAccounts.get(0);
    }

    @Override
    public EmailAccount getByEmail(String email) {
        List<EmailAccount> emailAccounts = getHibernateTemplate().find("from EmailAccount where email = ?", email);

        if(emailAccounts.size() < 1) {
            LOGGER.warn("emailAccounts.size() < 1: {}", emailAccounts.size());

            return null;
        }

        return emailAccounts.get(0);

    }
}
