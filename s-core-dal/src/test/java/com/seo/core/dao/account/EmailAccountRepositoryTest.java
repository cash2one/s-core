package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.EmailAccountRepository;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.EmailAccount;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmailAccountRepositoryTest extends AbstractDaoTest {

    public static final String TEST_EMAIL = "testemail@testhost.com";
    public static final String TEST_HOST = "testhost.com";

    private EmailAccountRepository emailAccountRepository;

    @Inject
    public void setRepository(EmailAccountRepository emailAccountRepository) {
        this.repository = (BaseRepository)emailAccountRepository;
        this.emailAccountRepository = emailAccountRepository;
    }

    @Override
    public BaseModel createModel() {
        EmailAccount emailAccount = new EmailAccount();

        emailAccount.setHost(TEST_HOST);
        emailAccount.setEmail(TEST_EMAIL);

        return emailAccount;
    }

    @Test
    public void testFindByEmail() {
        EmailAccount emailAccount = (EmailAccount) createModel();

        emailAccount = emailAccountRepository.save(emailAccount);

        assertNotNull(emailAccount.getId());

        EmailAccount found = emailAccountRepository.findByEmail(TEST_EMAIL);

        assertNotNull(found);
        assertEquals(found.getEmail(), TEST_EMAIL);

        EmailAccount notFound = emailAccountRepository.findByEmail("notexisting@gmail.com");

        assertNull(notFound);
    }

    @Test
    public void testGetRandom() {
        BaseModel model = createModel();

        model = emailAccountRepository.save((EmailAccount)model);

        assertNotNull(model.getId());

        EmailAccount emailAccount = emailAccountRepository.getRandom();

        assertNotNull(emailAccount);
    }

    @Test
    public void testGetRandomByHost() {
        BaseModel model = createModel();

        model = emailAccountRepository.save((EmailAccount)model);

        assertNotNull(model.getId());

        EmailAccount emailAccount = emailAccountRepository.getRandomByHost(TEST_HOST);

        assertNotNull(emailAccount);
        assertEquals(emailAccount.getHost(), TEST_HOST);

        EmailAccount notFound = emailAccountRepository.getRandomByHost("notexisting.com");

        assertNull(notFound);
    }
}
