package com.plus.hospital.usercenter.controller;

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

    @PostMapping("/login/password")
    public LoginResponse loginPwd(@Validated @RequestBody LoginPasswordRequest loginPassword,
                                  @RequestHeader("platform") String platform) {
        return loginService.loginPassword(loginPassword);
    }

    @PostMapping("/login/smsCode")
    public LoginResponse loginSms(@RequestBody LoginSmsRequest loginSms,
                                  @RequestHeader("platform") String platform) {

        return null;
    }

    /**
     * 患者注册账号
     */
    @PostMapping("/register")
    public void registerPatient(@Validated @RequestBody RegisterRequest registerRequest) {
        loginService.registerUser(registerRequest);
    }
}
