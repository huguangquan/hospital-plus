package com.plus.hospital.usercenter.dao.mapper;

import com.plus.hospital.usercenter.dao.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2023-06-05
 */
public interface MenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 查询角色对应的菜单目录，并且去重处理
     * @param platform
     * @param roleList
     * @return
     */
    List<MenuEntity> selectDistinctRolesMenus(@Param("platform") String platform,
                                              @Param("roleList") List<Long> roleList);
}
