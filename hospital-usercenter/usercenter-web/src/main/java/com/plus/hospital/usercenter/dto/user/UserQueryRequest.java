package com.plus.hospital.usercenter.dto.user;

import com.plus.hospital.framework.core.bean.request.PageQueryRequest;
import lombok.Data;

/**
 * 用户查询请求类
 * @author huguangquan
 * 2023/11/2
 **/
@Data
public class UserQueryRequest extends PageQueryRequest {
    private String mobile;

    private String username;

    /**
     * 账号状态：0锁定1正常
     */
    private Integer accountStatus;

    /**
     * 是否实名认证，1是0否
     */
    private Integer verifiedFlag;

}
