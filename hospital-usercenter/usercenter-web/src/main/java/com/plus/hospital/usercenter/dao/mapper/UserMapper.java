package com.plus.hospital.usercenter.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.dto.user.UserQueryRequest;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 查询用户分页查询
     *
     * @param page
     * @param request
     * @return
     */
    List<UserInfoDTO> queryUserPageOrList(Page<UserInfoDTO> page, @Param("request") UserQueryRequest request);

    /**
     * 查询用户列表
     * @param request
     * @return
     */
    List<UserInfoDTO> queryUserPageOrList(@Param("request") UserQueryRequest request);
}
