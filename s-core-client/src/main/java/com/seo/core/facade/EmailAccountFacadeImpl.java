package com.seo.core.facade;

import com.seo.core.dao.EmailAccountDAO;
import com.seo.core.model.account.EmailAccount;
import com.seo.provider.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmailAccountFacadeImpl implements EmailAccountFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailAccountFacadeImpl.class);

    private EmailAccountDAO emailAccountDAO;

    public void setEmailAccountDAO(EmailAccountDAO emailAccountDAO) {
        this.emailAccountDAO = emailAccountDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EmailAccount getRandomByHost(String host) {
        LOGGER.debug("getting random email by host: {}", host);

        return emailAccountDAO.getRandomByHost(host);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EmailAccount getRandomEmail() {
        LOGGER.debug("getting random email");

        return emailAccountDAO.getRandom();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByEmail(String email) {
        LOGGER.debug("deleting email: {}", email);

        EmailAccount emailAccount = emailAccountDAO.getByEmail(email);
        emailAccountDAO.delete(emailAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createEmailAccount(String email, String host, String user, String password) {
        LOGGER.debug("creating email: {}", email);

        this.save(
                new EmailAccount(
                        email,
                        host,
                        user,
                        password
                )
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(EmailAccount emailAccount) {
        LOGGER.debug("saving email: {}", emailAccount.getEmail());

        emailAccountDAO.save(emailAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(long id) {
        LOGGER.debug("deleting email, id={}", id);

        EmailAccount emailAccount = this.getById(id);
        emailAccountDAO.delete(emailAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EmailAccount getById(long id) {
        LOGGER.debug("loading email, id={}", id);

        return emailAccountDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<EmailAccount> getAll() {
        LOGGER.debug("getting email accounts list");

        return emailAccountDAO.getAll();
    }
}
