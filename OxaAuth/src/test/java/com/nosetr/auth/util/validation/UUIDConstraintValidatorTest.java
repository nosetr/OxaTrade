package com.nosetr.auth.util.validation;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

/**
 * To test UUIDConstraintValidator
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@DisplayName("Test class for UUIDConstraintValidator")
class UUIDConstraintValidatorTest {

	@Spy
	private final UUIDConstraintValidator validator = new UUIDConstraintValidator();

	@Test
	public void whenUUIDIsValidatedUsingRegex_thenValidationSucceeds() {
		Assertions.assertTrue(validator.isValid(createUUID("923e4567-e89b-12d3-a456-426655440000"), null));
	}

	@Test
	public void whenEmptyUUID_thenValidationSucceeds() {
		Assertions.assertTrue(validator.isValid(createUUID("00000000-0000-0000-0000-000000000000"), null));
	}
	
	@Test
	public void whenUUIDIsNull_thenValidationSucceeds() {
		Assertions.assertTrue(validator.isValid(null, null));
	}

	/**
	 * Helper for UUID-creation
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.2
	 * @param  string
	 * @return
	 */
	private UUID createUUID(String string) {
		return UUID.fromString(string);
	}

}
