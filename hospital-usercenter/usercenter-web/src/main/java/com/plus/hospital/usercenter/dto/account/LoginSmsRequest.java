package com.plus.hospital.usercenter.dto.account;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 验证码登录请求
 * @author huguangquan
 * 2023/6/5
 **/
@Data
public class LoginSmsRequest {
    @NotNull(message = "手机号不可空")
    private String mobile;

    @NotNull(message = "短信验证码不可空")
    private String smsCode;
}
