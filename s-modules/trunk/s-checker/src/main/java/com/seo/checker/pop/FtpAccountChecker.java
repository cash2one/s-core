package com.seo.checker.pop;

public interface FtpAccountChecker {
    boolean checkAccount(String host, String login, String password);
}
