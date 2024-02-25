package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

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
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserDto map(UserEntity userEntity);

	@InheritInverseConfiguration
	UserEntity map(UserDto dto);

	UserEntity map(@Valid UserRegisterDto dto);

	UserEntity updateUserFromDto(UserUpdateDto dto, @MappingTarget UserEntity entity);

	@InheritInverseConfiguration
	UserEntity map(UserOAuth2Dto dto);
}
