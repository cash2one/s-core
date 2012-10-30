package com.seo.checker.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.IOException;

@Named
public class FtpAccountCheckerImpl implements FtpAccountChecker{
    private final static Logger LOGGER = LoggerFactory.getLogger(FtpAccountCheckerImpl.class);

    private final static Integer SUCCESS_CODE = 230;

    @Override
    public boolean checkAccount(String login, String password, String host) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(host);
            ftp.login(login, password);

            ftp.enterLocalPassiveMode();

            LOGGER.info("Connected to " + host + ".");
            LOGGER.info(ftp.getReplyString());

            if(SUCCESS_CODE.equals(ftp.getReplyCode())) {
                ftp.logout();

                return true;
            } else {
                ftp.disconnect();

                return false;
            }

        } catch (IOException e) {
            LOGGER.error("I/O error: " + e);
            return false;
        }
        catch (NullPointerException e) {
            LOGGER.error("NullPointer error: " + e);
            return false;
        }
        finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    // do nothing
                }
            }
        }
    }
}
