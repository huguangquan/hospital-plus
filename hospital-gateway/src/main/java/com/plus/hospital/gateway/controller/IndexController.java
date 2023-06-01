package com.plus.hospital.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 * @author huguangquan
 * 2023/5/23
 **/
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "welcome view gateway";
    }
}
