package com.plus.hospital.usercenter.dao.service;

import com.plus.hospital.usercenter.dao.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
public interface MenuService extends IService<MenuEntity> {
    List<MenuEntity> selectDistinctRolesMenus(String platform, List<Long> roleList);
}
