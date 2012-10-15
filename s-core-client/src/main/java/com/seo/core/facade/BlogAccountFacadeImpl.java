package com.seo.core.facade;

import com.seo.core.dao.BlogAccountDAO;
import com.seo.core.model.account.BlogAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;

public class BlogAccountFacadeImpl implements BlogAccountFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(BlogAccountFacadeImpl.class);

    private BlogAccountDAO blogAccountDAO;

    public void setBlogAccountDAO(BlogAccountDAO blogAccountDAO) {
        this.blogAccountDAO = blogAccountDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(BlogAccount blogAccount) {
        LOGGER.debug("saving blog accoun, id={}", blogAccount.getId());

        blogAccountDAO.save(blogAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(long id) {
        LOGGER.debug("deleting blog account, id={}", id);

        BlogAccount blogAccount = this.getById(id);
        blogAccountDAO.delete(blogAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BlogAccount getById(long id) {
        LOGGER.debug("getting blog account by id={}", id);

        return blogAccountDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BlogAccount> getAll() {
        LOGGER.debug("getting all blog accounts");

        return blogAccountDAO.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createAccount(String type, String url, String xmlRpcPath, String login, String password) {
        LOGGER.debug("creating new blog account, type={}", type);

        BlogAccount blogAccount = new BlogAccount(
                type,
                url,
                xmlRpcPath,
                login,
                password
        );

        this.save(blogAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BlogAccount getRandom() {
        LOGGER.debug("loading random blog account");

        return blogAccountDAO.getRandom();
    }
}
