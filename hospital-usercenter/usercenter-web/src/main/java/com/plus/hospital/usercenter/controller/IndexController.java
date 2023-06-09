package com.plus.hospital.usercenter.controller;

import com.plus.hospital.framework.core.annotations.CurrentAccountId;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huguangquan
 * 2023/6/2
 **/
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "welcome view usercenter";
    }
}
