package com.zyk.jhtest.repository;

import com.zyk.jhtest.domain.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntityRepository extends MongoRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

}
