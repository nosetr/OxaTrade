package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nosetr.auth.dto.UserOAuth2Dto;
import com.nosetr.auth.entity.UserEntity;

/**
 * Mapper for UserOAuth2Dto to UserEntity.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Mapper(componentModel = "spring")
public interface UserOAuth2Mapper {

	UserOAuth2Dto map(UserEntity userEntity);

	@InheritInverseConfiguration
	@Mapping(target = "password", ignore = true)
	UserEntity map(UserOAuth2Dto dto);
}
