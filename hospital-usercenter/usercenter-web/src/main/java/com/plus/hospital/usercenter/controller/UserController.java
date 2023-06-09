package com.plus.hospital.usercenter.controller;

import com.plus.hospital.framework.core.annotations.CurrentAccountId;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import com.plus.hospital.usercenter.enums.UserMedicalRoleEnum;
import com.plus.hospital.usercenter.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 患者端查看我的资料
     * @param accountId
     * @return
     */
    @GetMapping("/patient/mine")
    public UserInfoDTO getPatientMine(@CurrentAccountId Long accountId) {
        return userBusinessService.getUserInfo(accountId, UserMedicalRoleEnum.patient.getCode());
    }

    /**
     * 医生端查看我的资料
     * @param accountId
     * @return
     */
    @GetMapping("/doctor/mine")
    public UserInfoDTO getDoctorMine(@CurrentAccountId Long accountId) {
        return userBusinessService.getUserInfo(accountId, UserMedicalRoleEnum.doctor.getCode());
    }
}
