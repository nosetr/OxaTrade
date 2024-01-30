package com.nosetr.trade.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("items")
public class ProductEntity {

	@Id
	private UUID id;
	private String name;
	private float price;
	private boolean priceWithVat;
	private short vat;
	private short itemUnit;
	private float weight;
	private short weightUnit;
	private UUID parent;
	private String gtin;
	private String mpn;
	private String description;
	private String memo;
	private boolean enabled;
	private boolean inArchives;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
