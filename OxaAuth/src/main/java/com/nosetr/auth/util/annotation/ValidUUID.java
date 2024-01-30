package com.nosetr.auth.util.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.nosetr.auth.util.validation.UUIDConstraintValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation to check UUID validation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 * @see UUIDConstraintValidator
 */
@Documented
@Constraint(validatedBy = UUIDConstraintValidator.class)
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidUUID {

	String message() default "{validation.uuid.ValidUUID}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
