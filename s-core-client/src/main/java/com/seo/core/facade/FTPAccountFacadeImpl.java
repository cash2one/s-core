package com.seo.core.facade;

import com.seo.core.dao.FTPAccountDAO;
import com.seo.core.facade.exception.NoAccountAvailableException;
import com.seo.core.model.account.FTPAccount;
import com.seo.core.model.account.FTPAccountState;
import com.seo.provider.model.FTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FTPAccountFacadeImpl implements FTPAccountFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(FTPAccountFacadeImpl.class);

    private FTPAccountDAO ftpAccountDAO;

    public void setFtpAccountDAO(FTPAccountDAO ftpAccountDAO) {
        this.ftpAccountDAO = ftpAccountDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(FTPAccount ftpAccount) {
        LOGGER.debug("saving ftp account");

        ftpAccountDAO.save(ftpAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(long id) {
        LOGGER.debug("deleting ftp account, id={}", id);

        FTPAccount ftpAccount = this.getById(id);
        ftpAccountDAO.delete(ftpAccount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FTPAccount getById(long id) {
        LOGGER.debug("loading ftp account by id, id={}", id);

        return ftpAccountDAO.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<FTPAccount> getAll() {
        LOGGER.debug("getting ftp accounts list");

        return ftpAccountDAO.getAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createAccount(String url, String login, String password, String host, String prefix, String type) {
        LOGGER.debug("creating ftp account");

        this.save(
                new FTPAccount(
                        url,
                        host,
                        login,
                        password,
                        prefix,
                        type,
                        FTPAccountState.FREE
                )
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public FTPAccount getAccountByType(String type) {
        LOGGER.debug("loading random account by type, type={}", type);

        return ftpAccountDAO.getByType(type);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public synchronized FTPAccount reserveAccount() {
        LOGGER.debug("reserving ftp account");

        FTPAccount ftpAccount = ftpAccountDAO.getFreeAccount();
        if(ftpAccount == null) {
            throw new NoAccountAvailableException("no free ftp account");
        }

        ftpAccount.setState(FTPAccountState.RESERVED);
        this.save(ftpAccount);

        return ftpAccount;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int getFreeAccountCount() {
        return ftpAccountDAO.getFreeAccountCount();
    }
}
