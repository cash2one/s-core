package com.seo.auto.command.load;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.provider.FtpAccountProvider;
import com.seo.provider.model.FTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads and saves into application registry ftp account with provided prefix in name
 * 1. host - account host
 * 2. url - destination site url
 * 3. login - ftp account login
 * 4. password - ftp account password
 * 5. prefix - ftp local path prefix 
 */
public class LoadFTPAccountCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoadFTPAccountCommand.class);

    private final static String NAME = "Load FTP Command";

    private final static String HOST = "host";
    private final static String URL = "url";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String PREFIX = "prefix";

    private String prefix;
    private String type;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void execute(CommandClient commandClient) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("loading ftp account by type: " + type);
        }

        Registry registry = commandClient.getRegistry();
        initializeVariables(registry);

        FtpAccountProvider ftpAccountProvider = getProviderManager().getFtpAccountProvider();
        FTP ftpAccount = ftpAccountProvider.getAccountByType(type);

        registry.put(prefix + HOST, ftpAccount.getHost());
        registry.put(prefix + URL, ftpAccount.getUrl());
        registry.put(prefix + LOGIN, ftpAccount.getLogin());
        registry.put(prefix + PASSWORD, ftpAccount.getPassword());
        registry.put(prefix + PREFIX, ftpAccount.getPrefix());
    }

    @Override
    protected void initializeVariables(Registry registry) {
        prefix = evaluatePlaceholder(prefix, registry);
        type = evaluatePlaceholder(type, registry);
    }

    @Override
    public String toString() {
        return String.format("%s @ type=%s", NAME, type);
    }
}
