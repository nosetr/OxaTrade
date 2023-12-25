package com.nosetr.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * DTO for users login action.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // values will be SnakeCase
public class AuthRequestDto {
	private String email;
	private String password;
}
