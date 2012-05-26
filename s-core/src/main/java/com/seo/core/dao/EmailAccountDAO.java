package com.seo.core.dao;

import com.seo.core.model.account.EmailAccount;

import java.util.List;

public interface EmailAccountDAO {
    void save(EmailAccount emailAccount);
    void delete(EmailAccount emailAccount);
    EmailAccount getById(long emailAccountId);
    List<EmailAccount> getAll();
    EmailAccount getRandom();
    EmailAccount getRandomByHost(String type);
    EmailAccount getByEmail(String email);
}
