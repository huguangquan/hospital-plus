package com.plus.hospital.usercenter.service;

import com.plus.hospital.usercenter.dto.account.LoginPasswordRequest;
import com.plus.hospital.usercenter.dto.account.LoginResponse;
import com.plus.hospital.usercenter.dto.account.LoginSmsRequest;
import com.plus.hospital.usercenter.dto.account.RegisterRequest;

/**
 * 注册、登录业务接口
 * @author huguangquan
 * 2023/6/5
 **/
public interface LoginService {

    /**
     * 账号注册
     * @param registerRequest
     */
    void registerUser(RegisterRequest registerRequest);

    /**
     * 密码登录
     * @param loginPassword
     */
    LoginResponse loginPassword(LoginPasswordRequest loginPassword);

    /**
     * 验证码登录
     * @param loginSms
     */
    LoginResponse loginSmsCode(LoginSmsRequest loginSms);

}
