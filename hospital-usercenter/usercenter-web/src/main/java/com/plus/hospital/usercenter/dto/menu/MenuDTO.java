package com.plus.hospital.usercenter.dto.menu;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
     * 菜单归属平台
     */
    private String platform;

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
     * 菜单的路由name
     */
    private String router;

    /**
     * 菜单或按钮跳转路由
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子菜单集合
     */
    private List<MenuDTO> children = new ArrayList<>();
}
