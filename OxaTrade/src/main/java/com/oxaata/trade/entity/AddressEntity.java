package com.oxaata.trade.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for addresses-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("addresses")
public class AddressEntity {

	@Id
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
