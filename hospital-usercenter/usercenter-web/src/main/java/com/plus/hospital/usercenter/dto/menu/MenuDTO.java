package com.plus.hospital.usercenter.dto.menu;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 菜单menu实体DTO
 * @author huguangquan
 * 2023/10/30
 **/
@Data
public class MenuDTO {
    private Long id;

    /**
     * 所属父菜单ID
     */
    @NotNull(message = "父菜单不可为空")
    private Long pid;

    /**
     * 1PC 2小程序患者端 3小程序医生端
     */
    private Integer platform;

    /**
     * 菜单名
     */
    @NotEmpty(message = "菜单名称不可为空")
    private String menuName;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 类型：1菜单2按钮3接口地址
     */
    private Integer type;

    /**
     * 菜单或按钮跳转路由
     */
    private String router;

    /**
     * 图标
     */
    private String icon;
}
