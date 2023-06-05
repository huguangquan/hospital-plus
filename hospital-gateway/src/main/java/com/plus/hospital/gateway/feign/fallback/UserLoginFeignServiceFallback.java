package com.plus.hospital.gateway.feign.fallback;

import com.plus.hospital.gateway.feign.UserLoginFeignService;
import org.springframework.stereotype.Service;

/** 用户登录feign-client熔断补偿实现
 * @author huguangquan
 * 2023/6/2
 **/
@Service
public class UserLoginFeignServiceFallback implements UserLoginFeignService {
    @Override
    public void login(String s, String s1) {
        System.out.println("login fallback ");
    }
}
