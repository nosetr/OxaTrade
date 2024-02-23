package com.nosetr.library.validation;

import org.springframework.beans.BeanWrapperImpl;

import com.nosetr.library.annotation.FieldsValueMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Check if for example the password and confirmation password match or other
 * fields.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

	private String field;
	private String fieldMatch;
	private String message;

	/*
	 * Annotation initialization.
	 */
	@Override
	public void initialize(FieldsValueMatch constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
		this.message = constraintAnnotation.message();
	}

	/*
	 * Validation
	 */
	@Override
	public boolean isValid(
			Object value,
			ConstraintValidatorContext context
	) {

		Object fieldValue = new BeanWrapperImpl(value)
				.getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(value)
				.getPropertyValue(fieldMatch);

		boolean isValid = false;
		if (fieldValue != null) {
			isValid = fieldValue.equals(fieldMatchValue);
		}

		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context
					.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(field)
					.addConstraintViolation();
			context
					.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(fieldMatch)
					.addConstraintViolation();
		}

		return isValid;

	}

}
