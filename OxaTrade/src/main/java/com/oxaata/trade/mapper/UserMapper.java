package com.oxaata.trade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.oxaata.trade.dto.UserDto;
import com.oxaata.trade.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto map(UserEntity userEntity);

  @InheritInverseConfiguration
  UserEntity map(UserDto dto);
}
