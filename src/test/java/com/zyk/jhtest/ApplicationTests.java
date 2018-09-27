package com.zyk.jhtest;

import com.zyk.jhtest.domain.Account;
import com.zyk.jhtest.domain.UserEntity;
import com.zyk.jhtest.repository.AccountRepository;
import com.zyk.jhtest.repository.UserEntityRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JhTestApp.class)
public class ApplicationTests {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        userEntityRepository.deleteAll();
    }

    @Test
    public void testUser() throws Exception {

        // 创建三个User，并验证User总数
        userEntityRepository.save(new UserEntity(1L, "didi", 30));
        userEntityRepository.save(new UserEntity(2L, "mama", 40));
        userEntityRepository.save(new UserEntity(3L, "shit", 50));
        Assert.assertEquals(3, userEntityRepository.findAll().size());

        // 删除一个User，再验证User总数
        UserEntity u = userEntityRepository.findById(1l).orElse(null);
        userEntityRepository.delete(u);
        Assert.assertEquals(2, userEntityRepository.findAll().size());

        // 删除一个User，再验证User总数
        u = userEntityRepository.findByUsername("mama");
        userEntityRepository.delete(u);
        Assert.assertEquals(1, userEntityRepository.findAll().size());

    }


    @Test
    public void testAccount(){
        MongoProperties mongoProperties;
        accountRepository.save(new Account(1l, "zyk", "zyk"));
        System.out.println("-------------------------------------------------------------------");
    }
}
