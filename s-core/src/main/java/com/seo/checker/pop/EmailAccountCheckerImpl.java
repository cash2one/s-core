package com.seo.checker.pop;

import org.apache.commons.net.pop3.POP3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.IOException;
import java.net.SocketException;

@Named
public class EmailAccountCheckerImpl implements EmailAccountChecker {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailAccountCheckerImpl.class);

    private final static Integer POP3_PORT = 110;
    
    @Override
    public boolean checkAccount(String login, String password, String host) {
        POP3Client client = new POP3Client();
        try {

            LOGGER.info("Connecting to " + host);
            client.connect(host, POP3_PORT);

            if (client.login(login, password)) {
                LOGGER.info("account valid: " + login);

                return true;
            }

        } catch (SocketException e) {
            LOGGER.error("Socket error: " + e);
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e);
        } catch (NullPointerException e) {
            LOGGER.error("NullPointer Exception: " + e);
        } finally {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException e) {
                    LOGGER.error("I/O exception");
                }
            }
        }

        return false;
    }
}
