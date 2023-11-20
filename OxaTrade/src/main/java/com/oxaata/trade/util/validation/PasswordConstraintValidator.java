package com.oxaata.trade.util.validation;

import java.util.Arrays;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.oxaata.trade.util.annotation.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Rules for passwords validation:
 * <p>Password need at least 8 characters and at most 100 chars,
 * at least one upper-case character,
 * at least one lower-case character,
 * at least one digit character,
 * at least one symbol (special character)
 * and no whitespace.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   ValidPassword
 * @see   https://www.baeldung.com/java-passay
 * @see   https://dzone.com/articles/spring-boot-custom-password-validator-using-passay
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		// Constraint rule set
		PasswordValidator validator = new PasswordValidator(
				Arrays.asList(
						// needs at least 8 characters and at most 100 chars
						new LengthRule(8, 100),
						// at least one upper-case character
						new CharacterRule(EnglishCharacterData.UpperCase, 1),
						// at least one lower-case character
						new CharacterRule(EnglishCharacterData.LowerCase, 1),
						// at least one digit character
						new CharacterRule(EnglishCharacterData.Digit, 1),
						// at least one symbol (special character)
						new CharacterRule(EnglishCharacterData.Special, 1),
						// no whitespace
						new WhitespaceRule()
				)
		);
		// validating password with rule set
		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) { return true; }

		// if not valid, set messages
		List<String> messages = validator.getMessages(result);
		String messageTemplate = String.join(",", messages);

		context.buildConstraintViolationWithTemplate(messageTemplate)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();

		return false;

		//		return false;
		//		throw new UnprocessableEntityException(messageTemplate, "INVALID_PASSWORD");
		//		return this.callExceptionForMono(messageTemplate) != null;
	}

	//	Mono<Void> callExceptionForMono(String messageTemplate) {
	//		throw new UnprocessableEntityException(messageTemplate, "INVALID_PASSWORD");
	//	}

}
