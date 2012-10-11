package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.FTPAccountRepository;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.FTPAccount;

import javax.inject.Inject;

public class FTPAccountRepositoryTest extends AbstractDaoTest {

    private FTPAccountRepository ftpAccountRepository;

    @Inject
    public void setRepository(FTPAccountRepository ftpAccountRepository) {
        this.repository = (BaseRepository) ftpAccountRepository;
        this.ftpAccountRepository = ftpAccountRepository;
    }

    @Override
    public BaseModel createModel() {
        FTPAccount ftpAccount = new FTPAccount();

        ftpAccount.setType("byethost");

        return ftpAccount;
    }
}
