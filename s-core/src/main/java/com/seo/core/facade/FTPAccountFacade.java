package com.seo.core.facade;

import com.seo.core.model.account.FTPAccount;
import com.seo.provider.FtpAccountProvider;

import java.util.List;

public interface FTPAccountFacade extends FtpAccountProvider{
    void save(FTPAccount ftpAccount);
    void deleteById(long id);
    FTPAccount getById(long id);
    List<FTPAccount> getAll();
    FTPAccount reserveAccount();
    int getFreeAccountCount();
}
