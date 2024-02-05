package com.nosetr.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.nosetr.library.util.annotation.ValidEmail;
import com.nosetr.library.util.annotation.ValidPassword;

import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;

	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidPassword
	private String password;
}
