package com.nosetr.auth.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.nosetr.auth.enums.OAuth2ProvidersEnum;
import com.nosetr.auth.enums.UserRoleEnum;
import com.nosetr.auth.util.annotation.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for users registration with OAuth2.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Data
public class UserOAuth2Dto {

	private UUID id;

	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;

	@NotBlank(message = "{validation.field.NotBlank}")
	private OAuth2ProvidersEnum provider; // 'google' or 'facebook', etc.

	private UserRoleEnum userRole;

	private String title;

	@NotBlank(message = "{validation.field.NotBlank}")
	private String firstName;

	@NotBlank(message = "{validation.field.NotBlank}")
	private String lastName;

	private boolean enabled;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
