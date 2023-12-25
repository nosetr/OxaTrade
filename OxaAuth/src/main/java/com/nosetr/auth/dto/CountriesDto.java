package com.nosetr.auth.dto;

import lombok.Data;

/**
 * DTO for countries edit.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
public class CountriesDto {

	private String countryCode;
	private String countryName;
	private String phonePrefix;
	private boolean enabled;

}
