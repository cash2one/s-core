package com.seo.core.facade;

import com.seo.core.dao.AutoConfigDAO;
import com.seo.core.facade.request.CreateAutoConfigRequest;
import com.seo.core.model.AutoConfig;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public class AutoConfigFacadeImpl implements AutoConfigFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(AutoConfigFacadeImpl.class);

    private AutoConfigDAO autoConfigDAO;

    public void setAutoConfigDAO(AutoConfigDAO autoConfigDAO) {
        this.autoConfigDAO = autoConfigDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long autoConfigId) {
        LOGGER.debug("deleting autoconfig id={}", autoConfigId);

        AutoConfig autoConfig = this.getById(autoConfigId);
        autoConfigDAO.delete(autoConfig);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(AutoConfig autoConfig) {
        LOGGER.debug("saving autoconfig, id={}", autoConfig.getId());

        autoConfigDAO.save(autoConfig);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<AutoConfig> getAll() {
        List<AutoConfig> autoConfigs = autoConfigDAO.getAll();

        LOGGER.debug("loaded {} autoconfigs", autoConfigs.size());

        return autoConfigs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AutoConfig getById(Long autoConfigId) {
        LOGGER.debug("loading autoconfig by id, id={}", autoConfigId);

        return autoConfigDAO.getById(autoConfigId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(CreateAutoConfigRequest request) {
        LOGGER.debug("creating new auto config");

        try {
            String content = FileUtils.readFileToString(request.getContent());
            AutoConfig autoConfig = new AutoConfig(
                    null,
                    request.getName(),
                    content
            );

            this.save(autoConfig);
        } catch (IOException e) {
            LOGGER.error("i/o error: {}", e.getMessage());

            throw new RuntimeException("i/o error: " + e.getMessage());
        }
    }
}
