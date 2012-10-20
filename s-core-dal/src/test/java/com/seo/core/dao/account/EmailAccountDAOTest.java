package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.EmailAccountDAO;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.EmailAccount;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmailAccountDAOTest extends AbstractDaoTest {

    public static final String TEST_EMAIL = "testemail@testhost.com";
    public static final String TEST_HOST = "testhost.com";

    private EmailAccountDAO emailAccountDAO;

    @Inject
    public void setRepository(EmailAccountDAO emailAccountDAO) {
        this.repository = (BaseRepository) emailAccountDAO;
        this.emailAccountDAO = emailAccountDAO;
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

        emailAccount = emailAccountDAO.save(emailAccount);

        assertNotNull(emailAccount.getId());

        EmailAccount found = emailAccountDAO.findByEmail(TEST_EMAIL);

        assertNotNull(found);
        assertEquals(found.getEmail(), TEST_EMAIL);

        EmailAccount notFound = emailAccountDAO.findByEmail("notexisting@gmail.com");

        assertNull(notFound);
    }

    @Test
    public void testGetRandom() {
        BaseModel model = createModel();

        model = emailAccountDAO.save((EmailAccount)model);

        assertNotNull(model.getId());

        EmailAccount emailAccount = emailAccountDAO.getRandom();

        assertNotNull(emailAccount);
    }

    @Test
    public void testGetRandomByHost() {
        BaseModel model = createModel();

        model = emailAccountDAO.save((EmailAccount)model);

        assertNotNull(model.getId());

        EmailAccount emailAccount = emailAccountDAO.getRandomByHost(TEST_HOST);

        assertNotNull(emailAccount);
        assertEquals(emailAccount.getHost(), TEST_HOST);

        EmailAccount notFound = emailAccountDAO.getRandomByHost("notexisting.com");

        assertNull(notFound);
    }

    @Test
    public void testDeleteByEmail() {
        BaseModel model = createModel();

        model = emailAccountDAO.save((EmailAccount)model);

        assertNotNull(model.getId());

        emailAccountDAO.deleteByEmail(((EmailAccount) model).getEmail());

        EmailAccount foundEmailAccount = emailAccountDAO.findByEmail(((EmailAccount) model).getEmail());

        assertNull(foundEmailAccount);

    }
}
