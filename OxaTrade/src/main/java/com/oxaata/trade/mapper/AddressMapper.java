package com.oxaata.trade.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.oxaata.trade.dto.AddressDto;
import com.oxaata.trade.entity.AddressEntity;

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
