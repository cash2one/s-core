package com.seo.core.dao;

import com.seo.core.model.account.EmailAccount;

public interface EmailAccountRepositoryCustom {
    EmailAccount getRandom();
    EmailAccount getRandomByHost(String type);
    EmailAccount getByEmail(String email);
}
