package com.nosetr.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for countries-table
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("countries")
public class CountriesEntity {

	@Id
	private String countryCode;
	private String countryName;
	private String phonePrefix;
	private boolean enabled; // if usable for user

}
