package com.plus.hospital.usercenter.controller;

import com.plus.hospital.framework.core.annotations.CurrentAccountId;
import com.plus.hospital.usercenter.dto.menu.MenuDTO;
import com.plus.hospital.usercenter.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色菜单管理： 接口不提供菜单创建，开发人员自行进行菜单SQL的初始化
 * @author huguangquan
 * 2023/10/30
 **/
@RestController
@RequestMapping("/menu")
public class RoleMenuController {
    @Autowired
    private UserBusinessService userBusinessService;

    /**
     * 查询用户对应角色的菜单请求
     * @param accountId
     * @return
     */
    @GetMapping("/list")
    public List<MenuDTO> getUserRoleMenuList(@CurrentAccountId Long accountId, @RequestHeader("platform") String platform) {
        return userBusinessService.getUserRoleMenuList(accountId, platform);
    }

    /**
     * 修改菜单
     * @param menu
     */
    @PostMapping("/modify")
    public void modifyMenu(@Validated @RequestBody MenuDTO menu) {

    }
}
