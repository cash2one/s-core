package com.seo.core.service;

import com.seo.core.model.account.EmailAccount;

public interface EmailAccountManager {
    EmailAccount findRandomByHost(String host);

    EmailAccount findRandom();

    void deleteByEmail(String email);

    EmailAccount save(EmailAccount emailAccount);

    EmailAccount findByEmail(String email);
}
