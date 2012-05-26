package com.seo.provider;

import com.seo.provider.model.Email;

public interface EmailProvider {
    Email getRandomByHost(String type);
    Email getRandomEmail();
    void deleteByEmail(String email);
    void createEmailAccount(String email, String host, String user, String password, String type);
}
