package com.plus.hospital.usercenter.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 账号信息表
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
@Getter
@Setter
@TableName("t_user_account")
public class UserAccountEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 乐观锁
     */
    @Version
    private Integer revision;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private Integer updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 删除标记，1正常0删除
     */
    private Integer deleteFlag;

    /**
     * 手机号，登录账号
     */
    private String mobile;

    /**
     * 账号昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 加密后的用户密码
     */
    private String password;

    /**
     * 密码加密盐值
     */
    private String passSalt;

    /**
     * 微信openId
     */
    private String wechatOpenId;

    /**
     * 微信unionId
     */
    private String wechatUnionId;

    /**
     * 账号状态：0锁定1正常
     */
    private Integer status;

    /**
     * 是否是超级管理员，1是0否
     */
    private Integer isAdmin;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录平台
     */
    private String lastLoginPlatform;

    /**
     * 是否是默认密码，1是0否
     */
    private Integer defaultPassword;
}
