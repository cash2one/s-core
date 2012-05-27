package com.seo.useragent.store.filesystem;

import com.seo.useragent.store.UserAgentStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileUserAgentStore implements UserAgentStore{
    private final static Logger LOGGER = LoggerFactory.getLogger(TextFileUserAgentStore.class);

    private String storePath;

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    @Override
    public void save(String userAgent) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("appending useragent[" + userAgent + "] to file [" + storePath + "]");
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(storePath)
            );

            bufferedWriter.append(userAgent);
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());

            throw new RuntimeException("I/O error: " + e.getMessage());
        }
    }
}
