package com.seo.useragent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextFileUserAgentProvider implements UserAgentProvider, InitializingBean{
    private final static Logger LOGGER = LoggerFactory.getLogger(TextFileUserAgentProvider.class);

    private final static Random RANDOM = new Random();

    private List<String> useragents = new ArrayList<String>();

    private String storePath;

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        try {
            BufferedReader input = readFile();

            String line = null;
            while ((line = input.readLine()) != null) {
                useragents.add(line);
            }

        } catch(FileNotFoundException e) {
            LOGGER.error("File not found: " + e);

            throw new RuntimeException("File not found: " + e);
        } catch (IOException e) {
            LOGGER.error("IOexception: " + e);

            throw new RuntimeException("IOexception: " + e);
        }
    }

    private BufferedReader readFile() throws FileNotFoundException {
        BufferedReader input =  new BufferedReader(
                new InputStreamReader(
                        TextFileUserAgentProvider.class.getClassLoader().getResourceAsStream(storePath)
                )
        );
        return input;
    }

    @Override
    public String getRandomUserAgent() {
        String randomAgent = useragents.get(RANDOM.nextInt(useragents.size()));

        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("loading random useragent from textfile: {}", randomAgent);
        }

        return randomAgent;
    }
}
