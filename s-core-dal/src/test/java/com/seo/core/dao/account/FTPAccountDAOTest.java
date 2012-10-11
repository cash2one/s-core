package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.FTPAccountDAO;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.FTPAccount;
import com.seo.core.model.account.FTPAccountState;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FTPAccountDAOTest extends AbstractDaoTest {

    public static final String FTP_ACCOUNT_TYPE = "byethost";
    private FTPAccountDAO ftpAccountDAO;

    @Inject
    public void setRepository(FTPAccountDAO ftpAccountDAO) {
        this.repository = (BaseRepository) ftpAccountDAO;
        this.ftpAccountDAO = ftpAccountDAO;
    }

    @Override
    public BaseModel createModel() {
        FTPAccount ftpAccount = new FTPAccount();

        ftpAccount.setType(FTP_ACCOUNT_TYPE);

        return ftpAccount;
    }

    @Test
    public void testFindByType() {
        repository.save(createModel());

        FTPAccount account = ftpAccountDAO.findByType(FTP_ACCOUNT_TYPE);

        assertNotNull(account);
        assertEquals(account.getType(), FTP_ACCOUNT_TYPE);

        FTPAccount nonExisting = ftpAccountDAO.findByType("testtype");

        assertNull(nonExisting);
    }

    @Test
    public void testGetFreeAccount() {
        FTPAccount ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.USED);
        ftpAccountDAO.save(ftpAccount);

        FTPAccount foundAccount = ftpAccountDAO.getFreeAccount();

        assertNull(foundAccount);

        ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.FREE);
        ftpAccountDAO.save(ftpAccount);

        foundAccount = ftpAccountDAO.getFreeAccount();

        assertNotNull(foundAccount);
        assertEquals(foundAccount.getState(), FTPAccountState.FREE);
    }

    @Test
    public void testGetFreeAccountCount() {
        FTPAccount ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.USED);
        ftpAccountDAO.save(ftpAccount);

        ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.USED);
        ftpAccountDAO.save(ftpAccount);

        ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.USED);
        ftpAccountDAO.save(ftpAccount);

        ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.FREE);
        ftpAccountDAO.save(ftpAccount);

        ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.FREE);
        ftpAccountDAO.save(ftpAccount);

        ftpAccount = new FTPAccount();
        ftpAccount.setState(FTPAccountState.USED);
        ftpAccountDAO.save(ftpAccount);

        long count = ftpAccountDAO.getFreeAccountCount();

        assertEquals(2, count);
    }
}
