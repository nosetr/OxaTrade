package com.oxaata.trade.dto;

import java.time.LocalDateTime;

import com.oxaata.trade.enums.OAuth2ProvidersEnum;
import com.oxaata.trade.enums.UserRoleEnum;
import com.oxaata.trade.util.annotation.ValidEmail;

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

	private Long id;

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
