package com.seo.auto.facade;

import com.seo.message.MessageNotifier;

public interface ConfigFacade extends MessageNotifier{
    void processConfig(String config);
}
