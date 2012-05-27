package com.seo.auto.command.result;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.provider.FtpAccountProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates fto account
 */
public class SaveFTPAccountCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(SaveFTPAccountCommand.class);

    private final static String NAME = "Save FTP Account Command";

    private String url;
    private String login;
    private String password;
    private String host;
    private String prefix;
    private String type;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void initializeVariables(Registry registry) {
        this.url = evaluatePlaceholder(url, registry);
        this.login = evaluatePlaceholder(login, registry);
        this.password = evaluatePlaceholder(password, registry);
        this.host = evaluatePlaceholder(host, registry);
        this.prefix = evaluatePlaceholder(prefix, registry);
        this.type = evaluatePlaceholder(type, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());
        
        LOGGER.info("saving ftp account, login: " + login + ", url:" + url);

        FtpAccountProvider ftpAccountProvider = getProviderManager().getFtpAccountProvider();
        ftpAccountProvider.createAccount(url, login, password, host, prefix, type);
    }

    @Override
    public String toString() {
        return String.format("%s @ url=%s", NAME, url);
    }
}
