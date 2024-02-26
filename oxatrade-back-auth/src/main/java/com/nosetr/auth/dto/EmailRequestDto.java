package com.nosetr.auth.dto;

import com.nosetr.library.annotation.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for email to newsletters.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Data
public class EmailRequestDto {
	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;
	private Long newstheme;
}
