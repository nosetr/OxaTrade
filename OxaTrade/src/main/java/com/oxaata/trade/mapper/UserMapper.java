package com.oxaata.trade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.oxaata.trade.dto.UserDto;
import com.oxaata.trade.entity.UserEntity;

/**
 * Mapper between UserDto and UserEntity.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   https://www.baeldung.com/mapstruct-ignore-unmapped-properties#ignore-specific-fields
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "confirmPassword", ignore = true)
	UserDto map(UserEntity userEntity);

	@InheritInverseConfiguration
	@Mapping(target = "provider", ignore = true)
	@Mapping(target = "toDelete", ignore = true)
	UserEntity map(UserDto dto);
}
