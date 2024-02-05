package com.nosetr.auth.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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

	@Id
	private Long id;
	private String themaName;
	private String memo;

	/*
	 * The many-to-many relationship to NewsletterEntity
	 */
	@Builder.Default
	private Set<NewsletterEntity> emails = new HashSet<>();

}