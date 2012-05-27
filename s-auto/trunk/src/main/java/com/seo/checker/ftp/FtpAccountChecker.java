package com.seo.checker.ftp;

public interface FtpAccountChecker {
    boolean checkAccount(String host, String login, String password);
}
