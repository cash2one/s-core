package com.seo.email;

import com.seo.email.model.EmailMessage;
import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class EmailClientImpl implements EmailClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailClientImpl.class);
    private POP3Client client = new POP3Client();


    private EmailClientImpl() {
    }

    public static EmailClient newInstance() {
        return new EmailClientImpl();
    }

    @Override
    public List<EmailMessage> receive(String login, String password, String host) {
        try {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Connecting to " + host);
            }

            client.connect(host, 110);

            client.login(login, password);

            List<EmailMessage> emails = new ArrayList<EmailMessage>();

            for (POP3MessageInfo message : client.listMessages()) {

                BufferedReader reader = new BufferedReader(client.retrieveMessage(message.number));
                String text = retrieveMessage(reader);

                emails.add(
                        new EmailMessage(text)
                );
                LOGGER.debug(text);
            }

            return emails;

        } catch (SocketException e) {
            LOGGER.error("Socket error: " + e);
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e);
        } catch (NullPointerException e) {
            LOGGER.info("NullPointer Exception: " + e);
        } finally {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException e) {
                    LOGGER.error("I/O exception");
                }
            }
        }

        return null;
    }

    private String retrieveMessage(Reader r) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(r);
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            r.close();

        }
        catch (Exception e) {
            LOGGER.error("Exception: " + e);
        }

        return sb.toString();
    }

    @Override
    public boolean checkEmail(String login, String password, String host) {
        LOGGER.debug("checking account {}:{}@{}", new Object[]{login, password, host});
        try {
            client.connect(host, 110);

            if (client.login(login, password)) {
                LOGGER.info("account valid");

                return true;
            }

        } catch (Exception e) {
            LOGGER.error("error: {}", e.getMessage());
        } finally {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException e) {
                    LOGGER.error("I/O exception on disconnect");
                }
            }
        }

        return false;
    }
}
