package com.nosetr.library.util.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.nosetr.library.util.validation.PasswordConstraintValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation to check passwords validation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see PasswordConstraintValidator
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword {

	String message() default "{validation.password.ValidPassword}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
