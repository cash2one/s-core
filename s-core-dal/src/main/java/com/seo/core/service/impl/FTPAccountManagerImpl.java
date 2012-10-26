package com.seo.core.service.impl;

import com.seo.core.dao.FTPAccountDAO;
import com.seo.core.model.account.FTPAccount;
import com.seo.core.service.FTPAccountManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FTPAccountManagerImpl implements FTPAccountManager {

    @Inject
    private FTPAccountDAO ftpAccountDAO;

    @Override
    public FTPAccount save(FTPAccount account) {
        return ftpAccountDAO.save(account);
    }

    @Override
    public FTPAccount findRandomByType(String type) {
        return ftpAccountDAO.findRandomByType(type);
    }

    @Override
    public FTPAccount findById(Long id) {
        return ftpAccountDAO.findOne(id);
    }
}
