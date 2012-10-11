package com.seo.core.dao;

import com.seo.core.model.account.FTPAccount;
import org.springframework.data.jpa.repository.Query;

public interface FTPAccountRepository extends BaseRepository<FTPAccount, Long>{
    FTPAccount findByType(String type);
    @Query("select f from FTPAccount where f.accountState = 0 order by rand()")
    FTPAccount getFreeAccount();
    @Query("select count(f) from FTPAccount where f.accountState = 0 order by rand()")
    int getFreeAccountCount();
}
