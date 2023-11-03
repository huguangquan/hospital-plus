package com.plus.hospital.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plus.hospital.usercenter.dto.menu.MenuDTO;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.dto.user.UserQueryRequest;

import java.util.List;

/**
 * 用户+账户业务service
 * @author huguangquan
 * 2023/6/8
 **/
public interface UserBusinessService {

    /**
     * 用户分页查询
     * @param request
     * @return
     */
    Page<UserInfoDTO> queryUserPages(UserQueryRequest request);

    /**
     * 查询患者的用户信息
     * @param accountId
     * @param userMedicalRole
     * @return
     */
    UserInfoDTO getUserInfo(Long accountId, Integer userMedicalRole);

    /**
     * 查询用户在对应平台的角色菜单列表
     * @param accountId
     * @param platform
     * @return
     */
    List<MenuDTO> getUserRoleMenuList(Long accountId, String platform);

    /**
     * 查询用户在对应平台的角色菜单树
     * @param accountId
     * @param platform
     * @return
     */
    List<MenuDTO> getUserRoleMenuTree(Long accountId, String platform);
}