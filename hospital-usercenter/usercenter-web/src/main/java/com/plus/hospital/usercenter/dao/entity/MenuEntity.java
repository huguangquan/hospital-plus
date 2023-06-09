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
 * 菜单表
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
@Getter
@Setter
@TableName("t_menu")
public class MenuEntity {

    /**
     * 主键ID
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
     * 菜单所属平台，1PC 2小程序患者端 3小程序医生端
     */
    private Integer platform;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID，一级菜单值-1
     */
    private Long pid;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 类型：1菜单2按钮3接口地址
     */
    private Integer type;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 菜单路由
     */
    private String router;

    /**
     * 排序
     */
    private Integer sorted;
}
