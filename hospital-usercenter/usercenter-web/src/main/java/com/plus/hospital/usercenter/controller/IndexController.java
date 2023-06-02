package com.plus.hospital.usercenter.controller;

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
