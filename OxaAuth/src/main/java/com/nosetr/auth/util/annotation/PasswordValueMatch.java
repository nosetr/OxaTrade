package com.nosetr.auth.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nosetr.auth.util.validation.PasswordFieldsValueMatchValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation for password confirmation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   PasswordFieldsValueMatchValidator
 */
@Documented
@Constraint(validatedBy = PasswordFieldsValueMatchValidator.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValueMatch {

	String message() default "{validation.password.confirm}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String field();

	String fieldMatch();

	/**
	 * Check if the password and confirmation password match.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		PasswordValueMatch[] value();
	}
}
