package com.plus.hospital.usercenter.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plus.hospital.usercenter.constants.SystemConstant;
import com.plus.hospital.usercenter.dao.convert.MenuConvert;
import com.plus.hospital.usercenter.dao.entity.MenuEntity;
import com.plus.hospital.usercenter.dao.entity.UserAccountEntity;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.plus.hospital.usercenter.dao.entity.UserRoleEntity;
import com.plus.hospital.usercenter.dao.service.MenuService;
import com.plus.hospital.usercenter.dao.service.UserAccountService;
import com.plus.hospital.usercenter.dao.service.UserRoleService;
import com.plus.hospital.usercenter.dao.service.UserService;
import com.plus.hospital.usercenter.dto.menu.MenuDTO;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.dto.user.UserQueryRequest;
import com.plus.hospital.usercenter.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户+账户业务serviceImpl
 * @author huguangquan
 * 2023/6/8
 **/
@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MenuService menuService;

    @Override
    public Page<UserInfoDTO> queryUserPages(UserQueryRequest request) {
        return userService.queryUserPage(request);
    }

    @Override
    public UserInfoDTO getUserInfo(Long accountId, Integer userMedicalRole) {
        UserAccountEntity account = userAccountService.getById(accountId);
        if (null == account || SystemConstant.data_delete_y.equals(account.getDeleteFlag())) {
            return null;
        }
        LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(UserEntity::getAccountId, accountId)
                .eq(UserEntity::getDeleteFlag, SystemConstant.data_delete_n);
        if (null != userMedicalRole) {
            userWrapper.eq(UserEntity::getMedicalRole, userMedicalRole);
        }
        UserEntity userEntity = userService.getOne(userWrapper);
        if (null == userEntity) {
            return null;
        }

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAccountId(account.getId());
        userInfoDTO.setMobile(account.getMobile());
        userInfoDTO.setNickName(account.getNickName());
        userInfoDTO.setAvatar(account.getAvatar());
        userInfoDTO.setUserName(userEntity.getUserName());
        userInfoDTO.setIdCard(userEntity.getIdCard());
        userInfoDTO.setBirthday(DateUtil.format(userEntity.getBirthday(), DatePattern.NORM_DATE_PATTERN));
        userInfoDTO.setGender(userEntity.getGender());
        userInfoDTO.setVerifiedFlag(userEntity.getVerifiedFlag());

        // todo 用户角色
        userInfoDTO.setRoles(Arrays.asList(new String[]{"test"}));
        return userInfoDTO;
    }

    @Override
    public List<MenuDTO> getUserRoleMenuList(Long accountId, String platform) {
        LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(UserEntity::getAccountId, accountId)
                .eq(UserEntity::getDeleteFlag, SystemConstant.data_delete_n);
        UserEntity userEntity = userService.getOne(userWrapper);
        if (userEntity == null) {
            return null;
        }

        List<UserRoleEntity> userRoles = userRoleService.list(new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userEntity.getId()));
        if (CollectionUtils.isEmpty(userRoles)) {
            return null;
        }

        List<Long> userRoleIds = userRoles.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        List<MenuEntity> userMenuList = menuService.selectDistinctRolesMenus(platform, userRoleIds);
        if (CollectionUtils.isEmpty(userMenuList)) {
            return null;
        }

        return userMenuList.stream().map(entity -> MenuConvert.INSTANCE.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<MenuDTO> getUserRoleMenuTree(Long accountId, String platform) {
        List<MenuDTO> userRoleMenuList = this.getUserRoleMenuList(accountId, platform);
        if (CollectionUtils.isEmpty(userRoleMenuList)) {
            return null;
        }
        // 1. 获取所有根菜单
        List<MenuDTO> rootMenu = userRoleMenuList.stream().filter(menu -> menu.getPid() == -1).collect(Collectors.toList());
        // 2. 递归查询子菜单
        for (MenuDTO menu : rootMenu) {
            setChileMenuList(menu, userRoleMenuList);
        }
        return rootMenu;
    }

    /**
     * 递归遍历查询设置子菜单
     * @param currentMenu
     */
    private void setChileMenuList(MenuDTO currentMenu, List<MenuDTO> menuList) {
        List<MenuDTO> childMenus = menuList.stream().filter(menu -> menu.getPid().longValue() == currentMenu.getId().longValue())
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(childMenus)) {
            currentMenu.setChildren(childMenus);
            for (MenuDTO childMenu : childMenus) {
                setChileMenuList(childMenu, menuList);
            }
        }
    }
}
