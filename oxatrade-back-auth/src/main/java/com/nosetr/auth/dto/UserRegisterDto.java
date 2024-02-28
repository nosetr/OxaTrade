package com.nosetr.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nosetr.auth.entity.UserEntity;
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
 * {@link UserEntity}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 * @see   UserEntity
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

	private String title;

	@NotBlank(message = "{validation.field.NotBlank}")
	@Size(message = "{validation.firstname.size}", min = 2, max = 64)
	@JsonProperty("first_name")
	private String firstName;

	@NotBlank(message = "{validation.field.NotBlank}")
	@Size(message = "{validation.lastname.size}", min = 2, max = 64)
//	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
	@ValidPassword
	@NotBlank(message = "{validation.field.NotBlank}")
	private String password;

	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;

	private boolean newsletter;
}
