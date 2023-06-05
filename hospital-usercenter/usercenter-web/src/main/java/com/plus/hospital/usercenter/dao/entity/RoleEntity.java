package com.plus.hospital.usercenter.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
@Getter
@Setter
@TableName("t_role")
public class RoleEntity {

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
     * 角色名，展示用途
     */
    private String roleName;

    /**
     * 角色key，系统代码使用
     */
    private String roleKey;

    /**
     * 排序
     */
    private Integer sorted;
}
