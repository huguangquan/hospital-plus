package com.plus.hospital.usercenter.dto.user;

import lombok.Data;

/**
 * 用户信息实体
 * @author huguangquan
 * 2023/6/8
 **/
@Data
public class UserInfoDTO {
    private Long accountId;

    private String mobile;

    private String nickName;

    private String avatar;

    private String userName;

    private String idCard;

    private String birthday;

    private Integer gender;

    /**
     * 是否实名：1是0否
     */
    private Integer verifiedFlag;

}
