package com.plus.hospital.usercenter.dao.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.plus.hospital.usercenter.dao.mapper.UserMapper;
import com.plus.hospital.usercenter.dao.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.dto.user.UserQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserInfoDTO> queryUserPage(UserQueryRequest request) {
        Page<UserInfoDTO> page = new Page<>(request.getPageNo(), request.getPageSize());
        List<UserInfoDTO> list = userMapper.queryUserPageOrList(page, request);
        // todo 手机号和身份证号脱敏
        if (!CollectionUtils.isEmpty(list)) {
            page.setRecords(list);
        }
        return page;
    }

    @Override
    public List<UserInfoDTO> queryUserList(UserQueryRequest request) {
        return userMapper.queryUserPageOrList(request);
    }
}
