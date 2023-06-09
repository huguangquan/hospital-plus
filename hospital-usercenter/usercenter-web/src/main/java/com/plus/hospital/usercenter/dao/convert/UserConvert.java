package com.plus.hospital.usercenter.dao.convert;

import com.plus.hospital.usercenter.dao.entity.UserEntity;
import com.plus.hospital.usercenter.dto.account.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * User DTO和Entity相互转换器
 * @author huguangquan
 * 2023/6/6
 **/
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(target = "birthday", source = "birthDate", dateFormat = "yyyy-MM-dd")
    UserEntity toEntity(RegisterRequest registerRequest);

}
