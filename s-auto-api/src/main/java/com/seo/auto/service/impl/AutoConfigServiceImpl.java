package com.seo.auto.service.impl;

import com.seo.auto.service.AutoConfigService;
import com.seo.core.model.AutoConfig;
import com.seo.core.service.AutoConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class AutoConfigServiceImpl implements AutoConfigService {

    private static final Logger logger = LoggerFactory.getLogger(AutoConfigServiceImpl.class);

    @Inject
    private AutoConfigManager autoConfigManager;

    @Override
    public List<AutoConfig> getAll() {
        logger.debug("getting auto configs list");

        return autoConfigManager.getAll();
    }

    @Override
    public AutoConfig save(AutoConfig autoConfig) {
        logger.debug("saving auto config, id={}, name={}", autoConfig.getId(), autoConfig.getName());

        return autoConfigManager.save(autoConfig);
    }
}
