package com.seo.core.facade.auto.task;

import com.seo.core.concurrency.task.AbstractMessageTask;
import com.seo.core.facade.AutoConfigFacade;
import com.seo.core.facade.auto.AutoConfigAdaptor;
import com.seo.core.model.AutoConfig;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class RunAutoConfigTask extends AbstractMessageTask{
    private AutoConfigFacade autoConfigFacade;
    private AutoConfigAdaptor autoConfigAdaptor;

    public void setAutoConfigAdaptor(AutoConfigAdaptor autoConfigAdaptor) {
        this.autoConfigAdaptor = autoConfigAdaptor;
    }

    public void setAutoConfigFacade(AutoConfigFacade autoConfigFacade) {
        this.autoConfigFacade = autoConfigFacade;
    }

    private long autoConfigId;

    public void setAutoConfigId(long autoConfigId) {
        this.autoConfigId = autoConfigId;
    }

    @Override
    public String toString() {
        return String.format("%s @ id=%d", "autoconfig", autoConfigId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run() {
        AutoConfig autoConfig = autoConfigFacade.getById(autoConfigId);

        autoConfigAdaptor.setMessageListener(getMessageListener());
        autoConfigAdaptor.execute(autoConfig);
    }
}
