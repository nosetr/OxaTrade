package com.nosetr.auth.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nosetr.auth.dto.EmailDto;
import com.nosetr.auth.dto.NewsletterDto;
import com.nosetr.auth.entity.NewsletterEntity;

/**
 * Mapper between NewsletterDto and NewsletterEntity / NewsthemaEntity
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Mapper(componentModel = "spring")
public interface NewsletterMapper {

//	@Mapping(source = "newsthemen.thema_name", target = "themaName")
//	@Mapping(source = "newsthemen.memo", target = "themaMemo")
	@Mapping(target = "themaMemo", ignore = true)
	@Mapping(target = "themaName", ignore = true)
	NewsletterDto map(NewsletterEntity entity);

	@InheritInverseConfiguration
	@Mapping(target = "themenEntities", ignore = true)
	NewsletterEntity map(NewsletterDto dto);

	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "lastUpdate", ignore = true)
	@Mapping(target = "themenEntities", ignore = true)
	NewsletterEntity map(EmailDto emailDto);
}
