package com.nosetr.auth.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nosetr.auth.entity.UserEntity;
import com.nosetr.auth.enums.UserRoleEnum;
import com.nosetr.library.annotation.ValidEmail;
import com.nosetr.library.annotation.ValidPassword;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for users registration.
 * <p> All field, but not a newsletter are equals to
 * {@link UserEntity}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   UserEntity
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private UUID id;

	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
	@ValidPassword
	@NotBlank(message = "{validation.field.NotBlank}")
	private String password;

	private UserRoleEnum userRole;

	private String title;

	@NotBlank(message = "{validation.field.NotBlank}")
	@Size(message = "{validation.firstname.size}", min = 2, max = 64)
	//	@JsonProperty("first_name")
	private String firstName;

	@NotBlank(message = "{validation.field.NotBlank}")
	@Size(message = "{validation.lastname.size}", min = 2, max = 64)
	private String lastName;

	private boolean enabled;

	private boolean newsletter;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
