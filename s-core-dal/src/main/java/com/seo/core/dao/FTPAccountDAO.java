package com.seo.core.dao;

import com.seo.core.model.account.FTPAccount;
import org.springframework.data.jpa.repository.Query;

public interface FTPAccountDAO extends BaseRepository<FTPAccount, Long>{
    FTPAccount findByType(String type);
    @Query("select f from FTPAccount f where f.state = 0 order by rand()")
    FTPAccount getFreeAccount();
    @Query("select count(f) from FTPAccount f where f.state = 0 order by rand()")
    long getFreeAccountCount();
}
