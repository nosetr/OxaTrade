package com.oxaata.trade.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for organizations-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("organizations")
public class OrganizationEntity {

	@Id
	private Long id;
	private String orgName;
	private String email;
	private String phone;
	private boolean enabled;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean toDelete;
	
}
