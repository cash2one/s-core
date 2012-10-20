package com.seo.provider.impl;

import com.seo.core.model.account.EmailAccount;
import com.seo.core.service.EmailAccountManager;
import com.seo.provider.EmailProvider;
import com.seo.provider.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmailProviderImpl implements EmailProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailProviderImpl.class);

    @Inject
    private EmailAccountManager emailAccountManager;

    @Override
    public EmailAccount getRandomByHost(String host) {
        LOGGER.debug("getting random email by host: {}", host);

        return emailAccountManager.findRandomByHost(host);
    }

    @Override
    public EmailAccount getRandomEmail() {
        LOGGER.debug("getting random email");

        return emailAccountManager.findRandom();
    }

    @Override
    public void deleteByEmail(String email) {
        LOGGER.debug("deleting account, email: {}", email);

        emailAccountManager.deleteByEmail(email);
    }

    @Override
    public void createEmailAccount(String email, String host, String user, String password) {
        LOGGER.debug("creation email account: {}", email);

        EmailAccount emailAccount = new EmailAccount(email, host, user, password);

        emailAccountManager.save(emailAccount);
    }
}
