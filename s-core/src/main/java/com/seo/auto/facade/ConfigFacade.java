package com.seo.auto.facade;

import com.seo.auto.model.Project;
import com.seo.message.MessageNotifier;

public interface ConfigFacade {
    Project processConfig(String config);
    boolean validateConfig(String config);
}
