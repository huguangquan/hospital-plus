package com.plus.hospital.usercenter.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
@Getter
@Setter
@TableName("t_user")
public class UserEntity {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 乐观锁
     */
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
     * 用户姓名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别，1男0女
     */
    private Integer gender;

    /**
     * 医疗业务中的身份角色，0患者1医生
     */
    private Integer medicalRole;

    /**
     * 账号ID
     */
    private Long accountId;

    /**
     * 是否实名认证，1是0否
     */
    private Integer verifiedFlag;

    /**
     * 身份证正面照url
     */
    private String idCardFrontUrl;

    /**
     * 身份证背面照url
     */
    private String idCardBackUrl;
}
