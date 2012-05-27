package com.seo.core.dao;

import com.seo.core.model.account.FTPAccount;

import java.util.List;

public interface FTPAccountDAO {
    void save(FTPAccount ftpAccount);
    void delete(FTPAccount ftpAccount);
    FTPAccount getById(long id);
    List<FTPAccount> getAll();
    FTPAccount getByType(String type);
    FTPAccount getFreeAccount();
    int getFreeAccountCount();
}
