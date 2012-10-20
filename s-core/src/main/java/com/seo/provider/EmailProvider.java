package com.seo.provider;

import com.seo.core.model.account.EmailAccount;
import com.seo.provider.model.Email;

public interface EmailProvider {
    EmailAccount getRandomByHost(String type);
    EmailAccount getRandomEmail();
    void deleteByEmail(String email);
    void createEmailAccount(String email, String host, String user, String password);
}
