package com.plus.hospital.usercenter.enums;

/**
 * 用户在医疗业务中的角色
 *
 * @author huguangquan
 * 2023/6/7
 **/
public enum UserMedicalRoleEnum {
    patient(0, "患者"),
    doctor(1, "医生");

    private Integer code;
    private String desc;

    private UserMedicalRoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
