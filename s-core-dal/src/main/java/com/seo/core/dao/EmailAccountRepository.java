package com.seo.core.dao;

import com.seo.core.model.account.EmailAccount;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmailAccountRepository extends BaseRepository<EmailAccount, Long> {
    @Query("select e from EmailAccount e order by rand()")
    EmailAccount getRandom();
    @Query("select e from EmailAccount e where e.host = ?1 order by rand()")
    EmailAccount getRandomByHost(String host);
    EmailAccount findByEmail(String email);
}
