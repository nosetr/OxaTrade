package com.oxaata.trade.dto;

import com.oxaata.trade.entity.UserEntity;

import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for users updating.
 * <p> All field, but not confirmPassword are equals to {@link UserEntity}
 * <p>No required fields.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   UserEntity
 */
@Data
public class UserUpdateDto {
	
	private String title;

  @Size(message = "{validation.firstname.size}", min = 2, max = 64)
	private String firstName;

  @Size(message = "{validation.lastname.size}", min = 2, max = 64)
	private String lastName;
}
