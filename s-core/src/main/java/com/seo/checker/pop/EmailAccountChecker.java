package com.seo.checker.pop;

public interface EmailAccountChecker {
    boolean checkAccount(String login, String password, String host);
}
