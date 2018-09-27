package com.zyk.jhtest.repository;

import com.zyk.jhtest.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, Long>{

    Account findAccountByAccountName(String accountName);
}
