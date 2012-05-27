package com.seo.auto.command.check;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.exception.CommandExecutionFailedException;
import com.seo.checker.ftp.FtpAccountChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command which tries to login into provided ftp account and breaks project execution if failed
 */
public class CheckFTPAccountCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(CheckFTPAccountCommand.class);

    private final static String NAME = "Check FTP Account Command";

    private String host;
    private String login;
    private String password;

    public void setHost(String host) {
        this.host = host;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        host = evaluatePlaceholder(host, registry);
        login = evaluatePlaceholder(login, registry);
        password = evaluatePlaceholder(password, registry);
    }

    @Override
    public void execute(CommandClient commandClient) throws CommandExecutionFailedException {
        initializeVariables(commandClient.getRegistry());

        FtpAccountChecker ftpAccountChecker = getProviderManager().getFtpAccountChecker();

        boolean accountOk = ftpAccountChecker.checkAccount(login, password, host);
        if(accountOk) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("check passed - login: " + login + ", password: " + password + ", host: " + host);
            }
        } else {
            LOGGER.error("check failed - login: " + login + ", password: " + password + ", host: " + host);

            throw new CommandExecutionFailedException("check failed - login: " + login + ", password: " + password + ", host: " + host);
        }
    }

    @Override
    public String toString() {
        return String.format("%s @ login=%s, host=%s", NAME, login, host);
    }
}
