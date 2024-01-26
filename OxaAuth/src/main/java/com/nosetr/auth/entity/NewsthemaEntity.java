package com.nosetr.auth.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for newsthema-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("newsthema")
public class NewsthemaEntity {

	@Id @GeneratedValue
	private Long id;
	private String themaName;
	private String memo;

	/*
	 * see: https://www.baeldung.com/jpa-many-to-many
	 */
	@ManyToMany(mappedBy = "themenEntities")
	private Set<NewsletterEntity> emailEntities;
	
}