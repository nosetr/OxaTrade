package com.oxaata.trade.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.enums.UserRoleEnum;
import com.oxaata.trade.util.annotation.PasswordValueMatch;
import com.oxaata.trade.util.annotation.ValidEmail;
import com.oxaata.trade.util.annotation.ValidPassword;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
					field = "password", fieldMatch = "confirmPassword", message = "{validation.password.confirm}"
			)
	}
)
@Data
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // not needed because of applications config
public class UserDto {

	private Long id;

	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
	@ValidPassword
	@NotBlank(message = "{validation.field.NotBlank}")
	private String password;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
	@NotBlank(message = "{validation.field.NotBlank}")
	private String confirmPassword;

	private UserRoleEnum userRole;
	
	private String title;

	@NotBlank(message = "{validation.field.NotBlank}")
  @Size(message = "{validation.firstname.size}", min = 2, max = 64)
	@JsonProperty("first_name")
	private String firstName;

	@NotBlank(message = "{validation.field.NotBlank}")
  @Size(message = "{validation.lastname.size}", min = 2, max = 64)
	private String lastName;

	private boolean enabled;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
