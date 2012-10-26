package com.seo.core.service;

import com.seo.core.AbstractDalTest;
import com.seo.core.model.account.FTPAccount;
import com.seo.core.model.account.FTPAccountState;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FTPAccountManagerTest extends AbstractDalTest {

    @Inject
    private FTPAccountManager ftpAccountManager;

    @Test
    public void testSave() {
        FTPAccount account = new FTPAccount("http://ftp.host.com", "host.com", "testlogin", "testpassword", "ftp_prefix", "hosttype", FTPAccountState.FREE);

        account = ftpAccountManager.save(account);

        assertNotNull(account);
        assertNotNull(account.getId());

        FTPAccount foundAccount = ftpAccountManager.findById(account.getId());

        assertNotNull(foundAccount);

        assertEquals(account.getHost(), foundAccount.getHost());
    }

    @Test
    public void testFindRandomByType() {
        FTPAccount account = new FTPAccount("http://ftp.host.com", "host.com", "testlogin", "testpassword", "ftp_prefix", "hosttype", FTPAccountState.FREE);
        FTPAccount account2 = new FTPAccount("http://ftp.host.com", "host.com", "testlogin", "testpassword", "ftp_prefix", "hosttype2", FTPAccountState.FREE);

        account = ftpAccountManager.save(account);
        account2 = ftpAccountManager.save(account2);

        assertNotNull(account);
        assertNotNull(account.getId());
        assertNotNull(account2);
        assertNotNull(account2.getId());

        FTPAccount foundAccount = ftpAccountManager.findRandomByType(account.getType());

        assertNotNull(foundAccount);
        assertEquals(foundAccount.getHost(), account.getHost());
    }
}
