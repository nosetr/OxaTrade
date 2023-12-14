package com.oxaata.trade.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Helper for password verification and encode.
 * <p> Implemented from {@link PasswordEncoder}.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   PasswordEncoder
 * @see   Base64
 */
@Component
public class PBFDK2Encoder implements PasswordEncoder {

	// Values from config-file
	@Value("${jwt.password.encoder.secret}")
	private String secret;
	@Value("${jwt.password.encoder.iteration}")
	private Integer iteration;
	@Value("${jwt.password.encoder.keylength}")
	private Integer keyLength;

	// Form of encoding
	private static final String SECRET_KEY_INSTANCE = "PBKDF2WithHmacSHA512";

	/*
	 * Try to encode a password.
	 */
	@Override
	public String encode(CharSequence rawPassword) {

		try {
			byte[] result = SecretKeyFactory.getInstance(SECRET_KEY_INSTANCE)
					.generateSecret(
							new PBEKeySpec(
									rawPassword.toString()
											.toCharArray(), secret.getBytes(), iteration, keyLength
							)
					)
					.getEncoded();
			return Base64.getEncoder()
					.encodeToString(result);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * Check, if passwords are equals.
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}
}
