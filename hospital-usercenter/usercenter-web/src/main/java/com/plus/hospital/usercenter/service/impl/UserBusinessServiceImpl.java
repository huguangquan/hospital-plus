package com.plus.hospital.usercenter.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.plus.hospital.usercenter.constants.SystemConstant;
import com.plus.hospital.usercenter.dao.entity.UserAccountEntity;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.plus.hospital.usercenter.dao.service.UserAccountService;
import com.plus.hospital.usercenter.dao.service.UserService;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public UserInfoDTO getUserInfo(Long accountId, Integer userMedicalRole) {
        UserAccountEntity account = userAccountService.getById(accountId);
        if (null == account || SystemConstant.data_delete_y.equals(account.getDeleteFlag())) {
            return null;
        }
        LambdaQueryWrapper<UserEntity> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(UserEntity::getAccountId, accountId)
                .eq(UserEntity::getMedicalRole, userMedicalRole)
                .eq(UserEntity::getDeleteFlag, SystemConstant.data_delete_n);
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
        return userInfoDTO;
    }
}
