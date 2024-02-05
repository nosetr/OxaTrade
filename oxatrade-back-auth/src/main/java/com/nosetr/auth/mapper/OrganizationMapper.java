package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.nosetr.auth.dto.OrganizationDto;
import com.nosetr.auth.entity.OrganizationEntity;

/**
 * Mapper between OrganizationDto and OrganizationEntity
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Mapper(componentModel = "spring")
public interface OrganizationMapper {

	OrganizationDto map(OrganizationEntity entity);

	@InheritInverseConfiguration
	OrganizationEntity map(OrganizationDto dto);
}
