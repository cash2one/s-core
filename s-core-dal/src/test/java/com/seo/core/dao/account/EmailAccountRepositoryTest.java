package com.seo.core.dao.account;

import com.seo.core.dao.AbstractDaoTest;
import com.seo.core.dao.BaseRepository;
import com.seo.core.dao.EmailAccountRepository;
import com.seo.core.model.BaseModel;
import com.seo.core.model.account.EmailAccount;

import javax.inject.Inject;

public class EmailAccountRepositoryTest extends AbstractDaoTest {

    @Inject
    public void setRepository(EmailAccountRepository emailAccountRepository) {
        this.repository = (BaseRepository)emailAccountRepository;
    }

    @Override
    public BaseModel createModel() {
        return new EmailAccount();
    }
}
