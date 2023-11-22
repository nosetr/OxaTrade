package com.oxaata.trade.util.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.oxaata.trade.util.validation.EmailConstraintValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Annotation to check email validation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see EmailConstraintValidator
 */
@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidEmail {

	String message() default "{validation.email.ValidEmail}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
