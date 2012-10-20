package com.seo.core.service;

import com.seo.core.AbstractDalTest;
import com.seo.core.model.account.EmailAccount;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EmailAccountManagerTest extends AbstractDalTest {

    @Inject
    private EmailAccountManager emailAccountManager;

    @Test
    public void testFindRandomEmailAccount() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        EmailAccount foundEmailAccount = emailAccountManager.findRandom();

        assertNotNull(foundEmailAccount);
        assertEquals(emailAccount.getEmail(), foundEmailAccount.getEmail());
    }

    @Test
    public void testFindRandomByHostAccount() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        EmailAccount foundEmailAccount = emailAccountManager.findRandomByHost(emailAccount.getHost());

        assertNotNull(foundEmailAccount);
        assertEquals(emailAccount.getEmail(), foundEmailAccount.getEmail());
    }

    @Test
    public void testSaveEmailAccount() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());
    }

    @Test
    public void testDeleteByEmail() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");
        EmailAccount emailAccount2 = new EmailAccount("test@test.com2", "test", "testuser2", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);
        emailAccount2 = emailAccountManager.save(emailAccount2);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        assertNotNull(emailAccount2);
        assertNotNull(emailAccount2.getId());

        emailAccountManager.deleteByEmail(emailAccount.getEmail());

        EmailAccount foundEmailAccount = emailAccountManager.findByEmail(emailAccount.getEmail());

        assertNull(foundEmailAccount);
    }

    @Test
    public void testFindByEmail() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        EmailAccount foundEmailAccount = emailAccountManager.findByEmail(emailAccount.getEmail());

        assertNotNull(foundEmailAccount);
        assertEquals(foundEmailAccount.getEmail(), emailAccount.getEmail());
    }
}
