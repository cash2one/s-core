package com.seo.core.service.impl;

import com.seo.core.dao.EmailAccountDAO;
import com.seo.core.model.account.EmailAccount;
import com.seo.core.service.EmailAccountManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmailAccountManagerImpl implements EmailAccountManager {

    @Inject
    private EmailAccountDAO emailAccountDAO;

    @Override
    public EmailAccount findRandomByHost(String host) {
        return emailAccountDAO.getRandomByHost(host);
    }

    @Override
    public EmailAccount findRandom() {
        return emailAccountDAO.getRandom();
    }

    @Override
    public void deleteByEmail(String email) {
        emailAccountDAO.deleteByEmail(email);
    }

    @Override
    public EmailAccount save(EmailAccount emailAccount) {
        return emailAccountDAO.save(emailAccount);
    }

    @Override
    public EmailAccount findByEmail(String email) {
        return emailAccountDAO.findByEmail(email);
    }
}
