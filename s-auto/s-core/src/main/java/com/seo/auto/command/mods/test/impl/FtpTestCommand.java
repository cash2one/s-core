package com.seo.auto.command.mods.test.impl;

import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.mods.test.TestCommand;
import com.seo.checker.ftp.FtpAccountChecker;
import com.seo.checker.ftp.FtpAccountCheckerImpl;
import com.seo.webclient.model.Response;

public class FtpTestCommand extends AbstractTestCommand implements TestCommand{
    private String login;
    private String password;
    private String host;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean test(Response response, Registry registry) {
        initializeVariables(registry);

        FtpAccountChecker ftpAccountChecker = new FtpAccountCheckerImpl();
        boolean result = ftpAccountChecker.checkAccount(host, login, password);

        return expected.equals(result);
    }

    private void initializeVariables(Registry registry) {
        this.login = evaluatePlaceholder(login, registry);
        this.password = evaluatePlaceholder(password, registry);
        this.host = evaluatePlaceholder(host, registry);
    }
}
