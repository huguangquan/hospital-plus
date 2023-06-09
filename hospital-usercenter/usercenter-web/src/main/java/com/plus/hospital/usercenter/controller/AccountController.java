package com.plus.hospital.usercenter.controller;

import com.plus.hospital.framework.core.annotations.CurrentAccountId;
import com.plus.hospital.framework.core.constants.SystemConstants;
import com.plus.hospital.usercenter.dto.account.LoginPasswordRequest;
import com.plus.hospital.usercenter.dto.account.LoginResponse;
import com.plus.hospital.usercenter.dto.account.LoginSmsRequest;
import com.plus.hospital.usercenter.dto.account.RegisterRequest;
import com.plus.hospital.usercenter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户操作
 *
 * @author huguangquan
 * 2023/6/5
 **/
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private LoginService loginService;

    /**
     * 密码登录
     *
     * @param loginPassword
     * @param platform
     * @return
     */
    @PostMapping("/login/password")
    public LoginResponse loginPwd(@Validated @RequestBody LoginPasswordRequest loginPassword,
                                  @RequestHeader("platform") String platform) {
        loginPassword.setPlatform(platform);
        return loginService.loginPassword(loginPassword);
    }

    /**
     * 验证码登录
     *
     * @param loginSms
     * @param platform
     * @return
     */
    @PostMapping("/login/smsCode")
    public LoginResponse loginSms(@Validated @RequestBody LoginSmsRequest loginSms,
                                  @RequestHeader("platform") String platform) {

        return null;
    }

    /**
     * 账号退出
     */
    @PostMapping("/logout")
    public void logout(@RequestHeader("platform") String platform,
                       @CurrentAccountId Long accountId) {
        loginService.logout(platform, accountId);
    }

    /**
     * 患者注册账号
     */
    @PostMapping("/register")
    public void registerPatient(@Validated @RequestBody RegisterRequest registerRequest) {
        loginService.registerUser(registerRequest);
    }
}
