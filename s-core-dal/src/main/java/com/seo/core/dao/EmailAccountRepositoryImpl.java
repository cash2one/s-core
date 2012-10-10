package com.seo.core.dao;

import com.seo.core.model.account.EmailAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class EmailAccountRepositoryImpl implements EmailAccountRepositoryCustom {
    @Override
    public EmailAccount getRandom() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EmailAccount getRandomByHost(String type) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EmailAccount getByEmail(String email) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
