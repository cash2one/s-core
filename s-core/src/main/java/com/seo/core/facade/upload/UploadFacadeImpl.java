package com.seo.core.facade.upload;

import com.seo.core.model.Doorway;
import com.seo.core.model.RedirectScript;
import com.seo.core.model.account.FTPAccount;
import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import com.seo.upload.DirectoryUploader;
import com.seo.upload.UploadMethodType;
import com.seo.upload.model.ServerInfo;
import com.seo.upload.model.UploadRequest;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

public class UploadFacadeImpl implements UploadFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadFacadeImpl.class);

    private final static String REDIRECT_DIRECTORY_NAME = "redirect";

    private DirectoryUploader directoryUploader;
    private MessageListener messageListener;

    public void setDirectoryUploader(DirectoryUploader directoryUploader) {
        this.directoryUploader = directoryUploader;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadDoorway(Doorway doorway) {
        FTPAccount ftpAccount = doorway.getFtpAccount();
        LOGGER.debug("uploading doorway, id = {}, to {}", doorway.getId(), "");

        messageListener.addMessage(new Message("starting uploading"));

        directoryUploader.setMessageListener(messageListener);
        directoryUploader.upload(
                new UploadRequest(
                        doorway.getPath(),
                        UploadMethodType.DIRECT_FTP,

                        new ServerInfo(
                                ftpAccount.getHost(),
                                ftpAccount.getLogin(),
                                ftpAccount.getPassword(),
                                ftpAccount.getPrefix()
                        ),
                        true
                )
        );

        messageListener.addMessage(new Message("finished uploading"));
    }

    @Override
    public void uploadRedirectScript(Doorway doorway) {
        LOGGER.debug("uploading redirect script for doorway id={}", doorway.getId());

        RedirectScript redirectScript = doorway.getRedirectScript();
        String redirectDirPath = generateRedirectDirPath(doorway);
        File redirectDir = new File(redirectDirPath);

        LOGGER.debug("saving redirect script {} to {}", redirectScript.getFileName(), redirectDirPath);
        try {
            FileUtils.forceMkdir(redirectDir);
            File redirectFile = new File(redirectDir, redirectScript.getFileName());

            FileUtils.writeStringToFile(redirectFile, redirectScript.getContent());

            FTPAccount ftpAccount = doorway.getFtpAccount();
            directoryUploader.upload(
                    new UploadRequest(
                            redirectDirPath,
                            UploadMethodType.DIRECT_FTP,

                            new ServerInfo(
                                    ftpAccount.getHost(),
                                    ftpAccount.getLogin(),
                                    ftpAccount.getPassword(),
                                    ftpAccount.getPrefix()
                            ),
                            true
                    )
            );
        } catch (IOException e) {
            LOGGER.error("i/o exception: {}", e.getMessage());

            throw new RuntimeException("i/o exception: " + e.getMessage());
        }


    }

    private String generateRedirectDirPath(Doorway doorway) {
        return doorway.getPath() + "/" + REDIRECT_DIRECTORY_NAME;
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
