package com.seo.core.dao;

import com.seo.core.model.account.FTPAccount;
import com.seo.core.model.account.FTPAccountState;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

public class FTPAccountDAOImpl extends HibernateDaoSupport implements FTPAccountDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(FTPAccountDAOImpl.class);

    @Override
    public void save(FTPAccount ftpAccount) {
        getHibernateTemplate().saveOrUpdate(ftpAccount);
    }

    @Override
    public void delete(FTPAccount ftpAccount) {
        getHibernateTemplate().delete(ftpAccount);
    }

    @Override
    public FTPAccount getById(long id) {
        return getHibernateTemplate().load(FTPAccount.class, id);
    }

    @Override
    public List<FTPAccount> getAll() {
        return getHibernateTemplate().loadAll(FTPAccount.class);
    }

    @Override
    public FTPAccount getByType(String type) {
        List<FTPAccount> ftpAccounts = getHibernateTemplate().find("from FTPAccount where type = ? order by rand()", type);

        if (ftpAccounts.size() < 1) {
            LOGGER.warn("ftpAccounts.size() < 1: {}", ftpAccounts.size());

            return null;
        }

        return ftpAccounts.get(0);
    }

    @Override
    public FTPAccount getFreeAccount() {
        List<FTPAccount> ftpAccounts = getHibernateTemplate().find("from FTPAccount where account_state = 0 order by rand()");

        if (ftpAccounts.size() < 1) {
            LOGGER.warn("ftpAccounts.size() < 1: {}", ftpAccounts.size());

            return null;
        }

        return ftpAccounts.get(0);
    }

    @Override
    public int getFreeAccountCount() {
        return (Integer) getHibernateTemplate().execute(
                new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
                        Criteria criteria = session.createCriteria(FTPAccount.class);
                        criteria.add(Restrictions.eq("state", FTPAccountState.FREE));

                        return criteria.list().size();
                    }
                }
        );
    }
}
