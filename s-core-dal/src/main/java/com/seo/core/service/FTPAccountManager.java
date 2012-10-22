package com.seo.core.service;

import com.seo.core.model.account.FTPAccount;

public interface FTPAccountManager {
    FTPAccount save(FTPAccount account);
    FTPAccount findRandomByType(String type);
    FTPAccount findById(Long id);
}
