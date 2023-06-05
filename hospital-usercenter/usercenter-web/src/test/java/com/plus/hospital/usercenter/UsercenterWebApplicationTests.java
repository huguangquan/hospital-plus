package com.plus.hospital.usercenter;

import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.plus.hospital.usercenter.dao.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class UsercenterWebApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        List<UserEntity> list = userService.list();
        System.out.println(list);
        Assert.notNull(list);
    }

}
