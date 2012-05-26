package com.seo.auto.runner;

import com.seo.auto.facade.ConfigFacade;
import com.seo.message.MessageListener;
import com.seo.message.model.Message;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class AutoConfigRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(AutoConfigRunner.class);

    private final static String CONFIG_FILE = "zxq.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        ConfigFacade configFacade = (ConfigFacade) applicationContext.getBean("configFacade");
        configFacade.setMessageListener(new MessageListener() {
            @Override
            public void addMessage(Message message) {
                LOGGER.debug(message.getContent());
            }
        });

        try {
            String config = IOUtils.toString(AutoConfigRunner.class.getClassLoader().getResourceAsStream(CONFIG_FILE));

            configFacade.processConfig(config);
        } catch (IOException e) {
            LOGGER.error("i/o error: {} {}", e.getMessage(), e.getStackTrace());
        }

    }
}
