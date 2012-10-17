package com.seo.auto.facade;

import com.seo.message.MessageNotifier;

public interface ConfigFacade {
    void processConfig(String config);
    boolean validateConfig(String config);
}
