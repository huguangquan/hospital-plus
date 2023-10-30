package com.plus.hospital.usercenter.dao.convert;

import com.plus.hospital.usercenter.dao.entity.MenuEntity;
import com.plus.hospital.usercenter.dto.menu.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单do和dto互转
 * @author huguangquan
 * 2023/10/30
 **/
@Mapper
public interface MenuConvert {

    MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuDTO toDto(MenuEntity entity);

    MenuEntity toEntity(MenuDTO dto);
}
