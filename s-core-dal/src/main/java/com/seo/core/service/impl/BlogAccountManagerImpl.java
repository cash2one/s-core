package com.seo.core.service.impl;

import com.seo.core.dao.BlogAccountDAO;
import com.seo.core.model.account.BlogAccount;
import com.seo.core.service.BlogAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BlogAccountManagerImpl implements BlogAccountManager {

    private static final Logger logger = LoggerFactory.getLogger(BlogAccountManagerImpl.class);

    @Inject
    private BlogAccountDAO blogAccountDAO;

    @Override
    public BlogAccount save(BlogAccount blogAccount) {
        logger.debug("saving blogaccount, id={}", blogAccount.getId());

        return blogAccountDAO.save(blogAccount);
    }

    @Override
    public BlogAccount findById(Long id) {
        logger.debug("finding blogaccount by id={}", id);

        return blogAccountDAO.findOne(id);
    }

    @Override
    public BlogAccount findByUrl(String url) {
        logger.debug("finding blogaccount by url={}", url);

        return blogAccountDAO.findByUrl(url);
    }
}
