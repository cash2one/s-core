package com.seo.checker.ftp;

public interface FtpAccountChecker {
    boolean checkAccount(String login, String password, String host);
}
