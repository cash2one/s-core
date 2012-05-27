package com.seo.core.facade;

import com.seo.core.model.account.EmailAccount;
import com.seo.provider.EmailProvider;

import java.util.List;

public interface EmailAccountFacade extends EmailProvider{
    void save(EmailAccount emailAccount);
    void deleteById(long id);
    EmailAccount getById(long id);
    List<EmailAccount> getAll();
}
