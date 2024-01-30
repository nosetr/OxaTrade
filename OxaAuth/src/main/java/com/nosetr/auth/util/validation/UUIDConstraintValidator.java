package com.nosetr.auth.util.validation;

import java.util.UUID;
import java.util.regex.Pattern;

import com.nosetr.auth.util.annotation.ValidUUID;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * UUID validation using regular expressions.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
public class UUIDConstraintValidator implements ConstraintValidator<ValidUUID, UUID> {

	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		if (value == null) return true;

		String uuidString = value.toString();

		if (uuidString.isEmpty()) return true;
		
		Pattern UUID_REGEX =
	      Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		
		return UUID_REGEX.matcher(uuidString).matches();
	}
}
