package com.seo.checker.pop;

public interface EmailAccountChecker {
    boolean checkAccount(String host, String login, String password);
}
