package com.seo.provider;

import com.seo.AbstractCoreTest;
import com.seo.core.model.account.EmailAccount;
import com.seo.core.service.EmailAccountManager;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class EmailProviderImpl extends AbstractCoreTest {

    @Inject
    private EmailProvider emailProvider;

    @Inject
    private EmailAccountManager emailAccountManager;

    @Test
    public void testGetRandomByHost() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        EmailAccount foundEmailAccount = emailProvider.getRandomByHost("test");

        assertNotNull(foundEmailAccount);
        assertEquals(foundEmailAccount.getEmail(), emailAccount.getEmail());
    }

    @Test
    public void testGetRandom() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        EmailAccount foundEmailAccount = emailProvider.getRandomEmail();

        assertNotNull(foundEmailAccount);
        assertEquals(foundEmailAccount.getEmail(), emailAccount.getEmail());
    }

    @Test
    public void testCreateEmailAccount() {
        emailProvider.createEmailAccount("test@test.com", "test", "testuser", "testpass");

        EmailAccount foundEmailAccount = emailAccountManager.findByEmail("test@test.com");

        assertNotNull(foundEmailAccount);
        assertEquals(foundEmailAccount.getEmail(), "test@test.com");
    }

    @Test
    public void testDeleteByEmail() {
        EmailAccount emailAccount = new EmailAccount("test@test.com", "test", "testuser", "testpass");

        emailAccount = emailAccountManager.save(emailAccount);

        assertNotNull(emailAccount);
        assertNotNull(emailAccount.getId());

        emailProvider.deleteByEmail("test@test.com");

        EmailAccount foundEmailAccount = emailAccountManager.findByEmail("test@test.com");

        assertNull(foundEmailAccount);
    }
}
