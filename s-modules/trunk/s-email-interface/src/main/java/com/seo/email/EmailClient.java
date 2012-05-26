package com.seo.email;

import com.seo.email.model.EmailMessage;

import java.util.List;

public interface EmailClient {
    List<EmailMessage> receive(String login, String password, String host);
    boolean checkEmail(String login, String password, String host);
}
