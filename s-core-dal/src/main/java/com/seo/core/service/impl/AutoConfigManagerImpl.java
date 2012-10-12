package com.seo.core.service.impl;

import com.seo.core.dao.AutoConfigDAO;
import com.seo.core.model.AutoConfig;
import com.seo.core.service.AutoConfigManager;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class AutoConfigManagerImpl implements AutoConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(AutoConfigManagerImpl.class);

    @Inject
    private AutoConfigDAO autoConfigDAO;

    @Override
    public List<AutoConfig> getAll() {
        logger.debug("executing getAll()");

        List<AutoConfig> autoConfigs = new ArrayList<AutoConfig>();

        CollectionUtils.addAll(autoConfigs, autoConfigDAO.findAll().iterator());

        return autoConfigs;
    }

    @Override
    public AutoConfig save(AutoConfig autoConfig) {
        logger.debug("saving autoconfig, id={}", autoConfig.getId());

        return autoConfigDAO.save(autoConfig);
    }
}
