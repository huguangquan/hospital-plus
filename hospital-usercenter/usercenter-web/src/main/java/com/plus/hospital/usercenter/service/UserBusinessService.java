package com.plus.hospital.usercenter.service;

import com.plus.hospital.usercenter.dto.user.UserInfoDTO;

/**
 * 用户+账户业务service
 * @author huguangquan
 * 2023/6/8
 **/
public interface UserBusinessService {

    /**
     * 查询患者的用户信息
     * @param accountId
     * @param userMedicalRole
     * @return
     */
    UserInfoDTO getUserInfo(Long accountId, Integer userMedicalRole);
}