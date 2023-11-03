package com.plus.hospital.usercenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plus.hospital.framework.core.annotations.CurrentAccountId;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.dto.user.UserQueryRequest;
import com.plus.hospital.usercenter.enums.UserMedicalRoleEnum;
import com.plus.hospital.usercenter.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户controller
 * @author huguangquan
 * 2023/6/8
 **/
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserBusinessService userBusinessService;

    /**
     * 我的资料
     * @param accountId
     * @return
     */
    @GetMapping("/mine")
    public UserInfoDTO getPatientMine(@CurrentAccountId Long accountId) {
        return userBusinessService.getUserInfo(accountId, null);
    }

    /**
     * 用户信息分页查询
     * @param accountId
     * @param userQueryRequest 用户查询请求实体
     * @return
     */
    @PostMapping("/page")
    public Page<UserInfoDTO> queryUserPage(@CurrentAccountId Long accountId,
                                           @RequestBody UserQueryRequest userQueryRequest) {
        return userBusinessService.queryUserPages(userQueryRequest);
    }
}
