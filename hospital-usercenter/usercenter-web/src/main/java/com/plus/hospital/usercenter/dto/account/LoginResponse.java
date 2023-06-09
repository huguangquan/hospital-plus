package com.plus.hospital.usercenter.dto.account;

import lombok.Data;

/**
 * @author huguangquan
 * 2023/6/5
 **/
@Data
public class LoginResponse {
    private String accessTokenValue;

    private String accessTokenName;

    private Long accessTokenTTL;
}
