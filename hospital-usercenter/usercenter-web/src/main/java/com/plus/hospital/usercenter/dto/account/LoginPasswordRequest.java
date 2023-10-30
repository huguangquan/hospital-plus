package com.plus.hospital.usercenter.dto.account;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 账号密码登录请求
 * @author huguangquan
 * 2023/6/5
 **/
@Data
public class LoginPasswordRequest {
    @NotNull(message = "登录手机号不可空")
    private String username;

    @NotNull(message = "登录密码不可空")
    private String password;

    /**
     * 登录平台：
     */
    private String platform;
}
