package com.plus.hospital.usercenter.dao.convert;

import com.plus.hospital.usercenter.dao.entity.UserAccountEntity;
import com.plus.hospital.usercenter.dto.account.RegisterRequest;
import com.plus.hospital.usercenter.dto.user.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * UserAccount DTO和Entity相互转换器
 * @author huguangquan
 * 2023/6/6
 **/
@Mapper
public interface UserAccountConvert {

    UserAccountConvert INSTANCE = Mappers.getMapper(UserAccountConvert.class);

    UserAccountEntity toEntity(RegisterRequest registerRequest);

}
