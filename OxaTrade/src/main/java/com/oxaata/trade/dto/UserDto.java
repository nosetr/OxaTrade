package com.oxaata.trade.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.enums.UserRole;
import com.oxaata.trade.util.annotation.PasswordValueMatch;
import com.oxaata.trade.util.annotation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for users registration.
 * <p> All field, but not confirmPassword are equals to {@link UserEntity}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   UserEntity
 */
@PasswordValueMatch.List(
	{
			@PasswordValueMatch(
					field = "password", fieldMatch = "confirmPassword", message = "Passwords do not match!"
			)
	}
)
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // make firstName to first_name...
public class UserDto {

	private Long id;

//	@NonNull
	@NotBlank(message = "email is mandatory")
	@Email(message = "email is invalid")
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
	@ValidPassword
//	@NonNull
	@NotBlank(message = "New password is mandatory")
	private String password;

	@ValidPassword
//	@NonNull
	@NotBlank(message = "Confirm Password is mandatory")
	private String confirmPassword;

	private UserRole userRole;

//	@NonNull
	@NotBlank(message = "first name is mandatory")
	private String firstName;

//	@NonNull
	@NotBlank(message = "last name is mandatory")
	private String lastName;

	private boolean enabled;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
