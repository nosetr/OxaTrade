package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.nosetr.auth.dto.AddressDto;
import com.nosetr.auth.entity.AddressEntity;

/**
 * Mapper between AddressDto and AddressEntity
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Mapper(componentModel = "spring")
public interface AddressMapper {

	AddressDto map(AddressEntity entity);

	@InheritInverseConfiguration
	AddressEntity map(AddressDto dto);
}
