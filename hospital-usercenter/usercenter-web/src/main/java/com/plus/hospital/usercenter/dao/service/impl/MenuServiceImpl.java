package com.plus.hospital.usercenter.dao.service.impl;

import com.plus.hospital.usercenter.dao.entity.MenuEntity;
import com.plus.hospital.usercenter.dao.mapper.MenuMapper;
import com.plus.hospital.usercenter.dao.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuEntity> selectDistinctRolesMenus(String platform, List<Long> roleList) {
        return menuMapper.selectDistinctRolesMenus(platform, roleList);
    }
}
