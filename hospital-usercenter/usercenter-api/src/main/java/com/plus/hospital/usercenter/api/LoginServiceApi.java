package com.plus.hospital.usercenter.api;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录service-feign接口API定义
 * @author huguangquan
 * 2023/6/2
 **/
public interface LoginServiceApi {

    /**
     * 手机账号登录
     * @param mobile
     * @param password
     */
    @RequestMapping("/login")
    void login(String mobile, String password);
}
