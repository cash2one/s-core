package com.seo.auto.command.email;

import com.seo.auto.client.CommandClient;
import com.seo.auto.client.registry.Registry;
import com.seo.auto.command.AbstractCommand;
import com.seo.auto.command.Command;
import com.seo.auto.command.mods.extract.ExtractCommand;
import com.seo.auto.command.mods.extract.Extractor;
import com.seo.auto.command.mods.extract.impl.ExtractorImpl;
import com.seo.email.EmailClient;
import com.seo.email.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves emails from remote pop3 server and extracts needed values
 */
public class EmailCommand extends AbstractCommand implements Command {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailCommand.class);

    private final static String NAME = "Email Command";
    private final static String NEW_LINE = "\n";

    private String login;
    private String password;
    private String host;
    private Integer sleep;
    private Integer attempts;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    private List<ExtractCommand> extractCommands = new ArrayList<ExtractCommand>();

    public void addExtractCommand(ExtractCommand extractCommand) {
        extractCommands.add(extractCommand);
    }

    @Override
    protected void initializeVariables(Registry registry) {
        login = evaluatePlaceholder(login, registry);
        password = evaluatePlaceholder(password, registry);
        host = evaluatePlaceholder(host, registry);
    }

    @Override
    public void execute(CommandClient commandClient) {
        initializeVariables(commandClient.getRegistry());

        int attempt = 0;
        boolean found = false;

        email_attempt:
        while (attempt < attempts && !found) {
            attempt++;

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted exception: " + e);
            }
            EmailClient client = commandClient.getEmailClient();

            Registry registry = commandClient.getRegistry();

            List<EmailMessage> emails = client.receive(login, password, host);
            for (EmailMessage email : emails) {
                String[] lines = email.getContent().split(NEW_LINE);


                for (String line : lines) {
                    Extractor extractor = new ExtractorImpl(line, registry);

                    for (ExtractCommand extractCommand : extractCommands) {
                        found = extractor.extract(extractCommand);

                        if (found) {
                            break email_attempt;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s @ login=%s, host=%s", NAME, login, host);
    }
}

