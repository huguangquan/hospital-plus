package com.plus.hospital.usercenter.dao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.dto.user.UserQueryRequest;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 分页查询用户
     * @param request
     * @return
     */
    Page<UserInfoDTO> queryUserPage(UserQueryRequest request);

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    List<UserInfoDTO> queryUserList(UserQueryRequest request);
}
