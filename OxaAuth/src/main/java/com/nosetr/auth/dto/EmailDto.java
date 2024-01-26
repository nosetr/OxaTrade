package com.nosetr.auth.dto;

import com.nosetr.auth.util.annotation.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for email to newsletters.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Data
public class EmailDto {
	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;
}
