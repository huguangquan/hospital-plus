package com.plus.hospital.usercenter.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.PhoneUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.plus.hospital.framework.core.exception.HospitalPlusException;
import com.plus.hospital.usercenter.constants.SystemConstant;
import com.plus.hospital.usercenter.dto.account.LoginPasswordRequest;
import com.plus.hospital.usercenter.dto.account.LoginResponse;
import com.plus.hospital.usercenter.dto.account.LoginSmsRequest;
import com.plus.hospital.usercenter.enums.ErrorCode;
import com.plus.hospital.usercenter.dao.convert.UserAccountConvert;
import com.plus.hospital.usercenter.dao.convert.UserConvert;
import com.plus.hospital.usercenter.dao.entity.UserAccountEntity;
import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.plus.hospital.usercenter.dao.service.UserAccountService;
import com.plus.hospital.usercenter.dao.service.UserService;
import com.plus.hospital.usercenter.dto.account.RegisterRequest;
import com.plus.hospital.usercenter.enums.AccountStatusEnum;
import com.plus.hospital.usercenter.enums.UserMedicalRoleEnum;
import com.plus.hospital.usercenter.service.LoginService;
import com.plus.hospital.usercenter.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.Objects;

/**
 * 注册、登录服务实现
 *
 * @author huguangquan
 * 2023/6/5
 **/
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(RegisterRequest registerRequest) {
        // 单独验证身份证和手机号的合法性
        if (!IdcardUtil.isValidCard(registerRequest.getIdCard())) {
            throw new HospitalPlusException(ErrorCode.invalidate_request_params.getCode(), "身份证格式不合法，请确认后重试!");
        }
        if (!PhoneUtil.isPhone(registerRequest.getMobile())) {
            throw new HospitalPlusException(ErrorCode.invalidate_request_params.getCode(), "手机号码格式不合法，请确认后重试!");
        }

        Date now = new Date();
        UserAccountEntity userAccountEntity = UserAccountConvert.INSTANCE.toEntity(registerRequest);
        userAccountEntity.setStatus(AccountStatusEnum.normal.getCode());
        userAccountEntity.setCreatedBy(SystemConstant.system_operate_user_id);
        userAccountEntity.setCreatedTime(now);
        String salt = PasswordUtil.generateString(6);
        userAccountEntity.setPassSalt(salt);
        if (StringUtils.isNotEmpty(registerRequest.getSignPassword())) {
            // 处理密码，用户注册时密码可选字段
            String password = PasswordUtil.unSignPassword(registerRequest.getSignPassword());
            userAccountEntity.setPassword(password);
            userAccountEntity.setDefaultPassword(0);
        } else {
            // 生成随机8位数密码
            String defaultPass = PasswordUtil.generateString(8);
            log.info("用户={},系统初始化密码={}", registerRequest.getUserName(), defaultPass);
            String password = PasswordUtil.generateEncodePassword(defaultPass, salt);
            userAccountEntity.setPassword(password);
            userAccountEntity.setDefaultPassword(1);
        }
        userAccountService.save(userAccountEntity);
        Long accountId = userAccountEntity.getId();

        UserEntity userEntity = UserConvert.INSTANCE.toEntity(registerRequest);
        userEntity.setAccountId(accountId);
        userEntity.setMedicalRole(UserMedicalRoleEnum.patient.getCode());
        userEntity.setCreatedBy(SystemConstant.system_operate_user_id);
        userEntity.setCreatedTime(now);
        userService.save(userEntity);

        // todo: sms短信通知用户初始化密码
    }

    @Override
    public LoginResponse loginPassword(LoginPasswordRequest loginPassword) {
        UserAccountEntity userAccount = userAccountService.getOne(
                new LambdaQueryWrapper<UserAccountEntity>()
                        .eq(UserAccountEntity::getMobile, loginPassword.getMobile())
                        .eq(UserAccountEntity::getDeleteFlag, SystemConstant.data_delete_n));
        if (Objects.isNull(userAccount)) {
            throw new HospitalPlusException(ErrorCode.login_error.getCode(), ErrorCode.login_error.getMessage());
        }

        if (AccountStatusEnum.locked.getCode().equals(userAccount.getStatus())) {
            throw new HospitalPlusException(ErrorCode.login_locked.getCode(), ErrorCode.login_locked.getMessage());
        }

        String password = PasswordUtil.unSignPassword(loginPassword.getPassword());
        if (!userAccount.getPassword().equals(PasswordUtil.generateEncodePassword(password, userAccount.getPassSalt()))) {
            // todo 密码错误次数处理以及是否状态锁定
            throw new HospitalPlusException(ErrorCode.login_error.getCode(), ErrorCode.login_error.getMessage());
        }

        // sa-token进行登录并返回token给前端(响应头写值)
        SaLoginModel saLoginModel = new SaLoginModel();
        saLoginModel.setDevice(loginPassword.getPlatform())
                .setIsWriteHeader(true);
        StpUtil.login(userAccount.getId(), saLoginModel);

        // 接口也返回token数据，处理前端无法使用cookie的场景
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessTokenName(StpUtil.getTokenName());
        loginResponse.setAccessTokenValue(StpUtil.getTokenValue());
        loginResponse.setAccessTokenTTL(StpUtil.getTokenTimeout());
        return loginResponse;
    }

    @Override
    public LoginResponse loginSmsCode(LoginSmsRequest loginSms) {

        return null;
    }

    @Override
    public void logout(String platform, Long loginAccountId) {
        StpUtil.logout(loginAccountId, platform);
    }
}
