package com.seo.core.facade.auto;

import com.seo.auto.facade.ConfigFacade;
import com.seo.core.model.AutoConfig;
import com.seo.message.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoConfigAdaptorImpl implements AutoConfigAdaptor{
    private final static Logger LOGGER = LoggerFactory.getLogger(AutoConfigAdaptorImpl.class);

    private ConfigFacade configFacade;
    private MessageListener messageListener;

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void setConfigFacade(ConfigFacade configFacade) {
        this.configFacade = configFacade;
    }

    @Override
    public void execute(AutoConfig autoConfig) {
        LOGGER.debug("executing config facade for config name={}, id={}", autoConfig.getName(), autoConfig.getId());

        configFacade.processConfig(autoConfig.getConfig());
    }
}
