package com.nosetr.auth.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * DTO for organization creation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
public class OrganizationDto {

	private Long id;
	private String orgName;
	private String email;
	private String phone;
	private boolean enabled;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String memo;
	
}
