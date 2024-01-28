package com.nosetr.auth.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nosetr.auth.dto.NewsletterDto;
import com.nosetr.auth.entity.NewsletterEntity;

/**
 * Mapper between NewsletterDto and NewsletterEntity / NewsthemaEntity
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface NewsletterMapper {

//	@Mapping(source = "newsthemen.thema_name", target = "themaName")
//	@Mapping(source = "newsthemen.memo", target = "themaMemo")
	@Mapping(target = "themaMemo", ignore = true)
	@Mapping(target = "themaName", ignore = true)
	NewsletterDto map(NewsletterEntity entity);

	@InheritInverseConfiguration
	@Mapping(target = "newsthemen", ignore = true)
	NewsletterEntity map(NewsletterDto dto);

//	@Mapping(target = "id", ignore = true)
//	@Mapping(target = "enabled", ignore = true)
//	@Mapping(target = "lastUpdate", ignore = true)
//	@Mapping(target = "newsthemen", ignore = true)
//	@Mapping(target = "newsthemen", qualifiedByName = "newsthemaDtoToNewsthemaEntitySet")
//	NewsletterEntity map(EmailDto emailDto);
	
//	@Named("newsthemaDtoToNewsthemaEntitySet")
//  default Set<NewsthemaEntity> newsthemenDtoToNewsthemaEntitySet(Set<NewsthemaDto> newsthemenDtoSet) {
//      return newsthemenDtoSet.stream()
//              .map(this::newsthemenDtoToNewsthemaEntity)
//              .collect(Collectors.toSet());
//  }
//	
//	@Named("newsthemaDtoToNewsthemaEntity")
//	NewsthemaEntity newsthemenDtoToNewsthemaEntity(NewsthemaDto newsthemenDto);
}
