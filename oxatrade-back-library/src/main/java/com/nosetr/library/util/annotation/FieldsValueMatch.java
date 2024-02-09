package com.nosetr.library.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nosetr.library.util.validation.FieldsValueMatchValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation for password confirmation or other fields to match.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   FieldsValueMatchValidator
 */
@Documented
@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

	String message() default "{validation.password.confirm}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String field();

	String fieldMatch();

	/**
	 * Check if the fields match.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		FieldsValueMatch[] value();
	}
}
