package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.nosetr.auth.dto.CountriesDto;
import com.nosetr.auth.entity.CountriesEntity;

/**
 * Mapper between CountriesDto and CountriesEntity
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Mapper(componentModel = "spring")
public interface CountriesMapper {

	CountriesDto map(CountriesEntity entity);

	@InheritInverseConfiguration
	CountriesEntity map(CountriesDto dto);
}
