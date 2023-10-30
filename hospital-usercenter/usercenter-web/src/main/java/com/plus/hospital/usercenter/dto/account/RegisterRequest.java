package com.plus.hospital.usercenter.dto.account;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户注册请求
 * @author huguangquan
 * 2023/6/6
 **/
@Data
public class RegisterRequest {
    @NotNull(message = "手机号不可空")
    private String mobile;

    @NotNull(message = "手机号不可空")
    private String idCard;

    @NotNull(message = "姓名不可空")
    private String userName;

    /**
     * 性别：1男0女
     */
    @NotNull(message = "性别不可空")
    private Integer gender;

    /**
     * 出生日期
     */
    @NotNull(message = "性别不可空")
    private String birthDate;

    /**
     * 账号注册的短信验证码
     */
    private String smsCode;

    /**
     * 前端传递的签名密码
     */
    private String password;
}
