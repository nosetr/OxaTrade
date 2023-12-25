package com.nosetr.auth.util.validation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.nosetr.auth.util.annotation.ValidPassword;

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
 * @see   https://www.passay.org/reference/
 * @see   https://www.baeldung.com/java-passay
 * @see   https://dzone.com/articles/spring-boot-custom-password-validator-using-passay
 * @see   https://www.baeldung.com/spring-classpath-file-access#2-using-value
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Value("classpath:msg/passay.properties")
	Resource messagesFile;

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {

		// Load message file:
		Properties props = new Properties();
		try {
			props.load(messagesFile.getInputStream());
		} catch (IOException e) {
			System.out.printf("{validation.password.FileNotFind}", messagesFile.getFilename());
		}
		MessageResolver resolver = new PropertiesMessageResolver(props);

		// Constraint rule set:
		PasswordValidator validator = new PasswordValidator(
				resolver, Arrays.asList(
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
	}
}
