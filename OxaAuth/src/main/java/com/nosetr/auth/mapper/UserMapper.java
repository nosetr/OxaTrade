package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserOAuth2Dto;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.dto.UserUpdateDto;
import com.nosetr.auth.entity.UserEntity;

import jakarta.validation.Valid;

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
	@Mapping(target = "newsletter", ignore = true)
	UserDto map(UserEntity userEntity);

	@InheritInverseConfiguration
	@Mapping(target = "provider", ignore = true)
	UserEntity map(UserDto dto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "userRole", ignore = true)
	@Mapping(target = "provider", ignore = true)
	UserEntity map(@Valid UserRegisterDto dto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "userRole", ignore = true)
	@Mapping(target = "provider", ignore = true)
	UserEntity updateUserFromDto(UserUpdateDto dto, @MappingTarget UserEntity entity);

	@InheritInverseConfiguration
	@Mapping(target = "password", ignore = true)
	UserEntity map(UserOAuth2Dto dto);
}
