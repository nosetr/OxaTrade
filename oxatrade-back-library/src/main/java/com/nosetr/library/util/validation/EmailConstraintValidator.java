package com.nosetr.library.util.validation;

import org.apache.commons.validator.routines.EmailValidator;

import com.nosetr.library.util.annotation.ValidEmail;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Email validation using Apache Commons Validator.
 * <p>
 * <a href="https://commons.apache.org/proper/commons-validator/">
 * Apache Commons Validator</a> provides the building blocks for both client
 * side validation and server side data validation.
 * <p>It may be used standalone or with a framework like Struts.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   ValidEmail
 * @see   https://commons.apache.org/proper/commons-validator/
 */
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value == null) return true;
		
		String email = value.toString();

		if (email.isEmpty()) return true;
		return EmailValidator.getInstance()
				.isValid(email);
	}
}
