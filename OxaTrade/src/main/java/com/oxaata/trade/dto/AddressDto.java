package com.oxaata.trade.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * DTO for address creation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
public class AddressDto {

	private Long id;
	private Long orgId;
	private String titleName;
	private String aliasName;
	private String streetName;
	private String houseName;
	private String districtName;
	private String cityName;
	private String zipName;
	private String stateName;
	private Long countryId;
	private String email;
	private String phone;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
