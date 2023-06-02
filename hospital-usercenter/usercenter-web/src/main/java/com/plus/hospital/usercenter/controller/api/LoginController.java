package com.plus.hospital.usercenter.controller.api;

import com.plus.hospital.usercenter.api.LoginServiceApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huguangquan
 * 2023/6/2
 **/
@RestController
public class LoginController implements LoginServiceApi {
    @Override
    public void login(String mobile, String password) {
        System.out.println("登录成功.");
    }
}
