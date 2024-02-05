package com.nosetr.auth.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO to response new bearer token after login.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // values will be SnakeCase
public class AuthResponseDto {

	private UUID userId;
	private String token;
	private Date issuedAt;
	private Date expiresAt;

}
