package com.plus.hospital.usercenter.enums;

/**
 * 账号状态枚举
 * @author huguangquan
 * 2023/6/6
 **/
public enum AccountStatusEnum {
    normal(1, "正常"),
    locked(0, "锁定");

    private Integer code;
    private String desc;

    private AccountStatusEnum(Integer code, String desc) {
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
