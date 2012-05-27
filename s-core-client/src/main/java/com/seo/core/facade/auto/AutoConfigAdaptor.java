package com.seo.core.facade.auto;

import com.seo.core.model.AutoConfig;
import com.seo.message.MessageNotifier;

public interface AutoConfigAdaptor extends MessageNotifier{
    void execute(AutoConfig autoConfig);
}
