package com.oxaata.trade.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.enums.UserRole;

import lombok.Data;

/**
 * DTO for users registration.
 * <p> All field are the same as in {@link UserEntity}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   UserEntity
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // make firstName to first_name...
public class UserDto {

	private Long id;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // not for output
	private String password;
	private UserRole userRole;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
