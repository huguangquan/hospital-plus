package com.plus.hospital.usercenter.enums;

/**
 * usercenter errorCode 错误码设置从1000~1999，系统类相关错误码使用hospital-framework-core中的errorCode
 * @author huguangquan
 * 2023/6/6
 **/
public enum ErrorCode {
    invalidate_request_params("1000", "请求参数不合法，请确认后重试!"),
    login_error("2000", "用户名或密码错误，请确认后重试!"),
    login_locked("2001", "账户已被锁定，请联系管理员!")
    ;

    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
