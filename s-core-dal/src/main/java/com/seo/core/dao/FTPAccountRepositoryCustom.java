package com.seo.core.dao;

import com.seo.core.model.account.FTPAccount;

public interface FTPAccountRepositoryCustom {
    FTPAccount getByType(String type);
    FTPAccount getFreeAccount();
    int getFreeAccountCount();
}
