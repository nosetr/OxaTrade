package com.nosetr.auth.service.impl;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nosetr.auth.dto.AuthResponseDto;
import com.nosetr.auth.entity.UserEntity;
import com.nosetr.auth.security.PBFDK2Encoder;
import com.nosetr.auth.service.SecurityService;
import com.nosetr.auth.service.UserService;
import com.nosetr.library.enums.ErrorEnum;
import com.nosetr.library.exception.AuthException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Make handling with authentication and tokens generation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Component
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService{

	private final UserService userService;
	private final PBFDK2Encoder passwordEncoder;

	// Values from config-file:
	@Value("${oxa.jwt.secret}")
	private String secret;
	@Value("${oxa.jwt.expiration}")
	private Integer expirationInSeconds;
	@Value("${oxa.jwt.issuer}")
	private String issuer;

	/**
	 * Begin users login with values from {@link com.nosetr.auth.dto.AuthRequestDto
	 * AuthRequestDto} and return mapped {@link AuthResponseDto} as Mono.
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param  email    String
	 * @param  password String
	 * @return          Mono<AuthResponseDto>
	 * @see             UserService
	 * @see             PBFDK2Encoder#matches(CharSequence, String)
	 */
	@Override
	public Mono<AuthResponseDto> authenticate(String email, String password) {
		// Find UserEntity by email and return AuthResponseDto:
		return userService.getUserByEmail(email)
				.flatMap(user -> {
					// Exception if users account is not active:
					if (!user.isEnabled()) {
						return Mono.error(new AuthException(ErrorEnum.USER_ACCOUNT_IS_DISABLED));
					}
					// Exception if password is invalid:
					if (!passwordEncoder.matches(password, user.getPassword())) {
						return Mono.error(new AuthException(ErrorEnum.INVALID_PASSWORD_IS_REQUESTED));
					}
					// Token generation:
					return Mono.just(
							generateToken(user).toBuilder()
									.userId(user.getId()) // add userId to claims
									.build()
					);
				})
				// If no user founded:
				.switchIfEmpty(Mono.error(new AuthException(ErrorEnum.INVALID_EMAIL_IS_REQUESTED)));
	}

	/**
	 * generateToken #1
	 * <p>Build claims as HashMap and return generateToken #2.
	 * 
	 * @autor       Nikolay Osetrov
	 * @since       0.1.0
	 * @param  user UserEntity
	 * @return      AuthResponseDto
	 */
	@SuppressWarnings("serial")
	private AuthResponseDto generateToken(UserEntity user) {
		Map<String, Object> claims = new HashMap<>() {
			{
				put("role", user.getUserRole());
				put("email", user.getEmail());
			}
		};
		// generateToken #2:
		return generateToken(
				claims, user.getId()
						.toString()
		);
	}

	/**
	 * generateToken #2
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  claims  Map<String, Object>
	 * @param  subject UserId as String
	 * @return         AuthResponseDto
	 */
	private AuthResponseDto generateToken(Map<String, Object> claims, String subject) {

		// Set expirationDate:
		Long expirationTimeInMillis = expirationInSeconds * 1000L;
		Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);

		// Set creations date:
		Date createdDate = new Date();

		// Encode oxa.jwt.secret from config-file:
		byte[] bytes = Decoders.BASE64.decode(
				Base64.getEncoder()
						.encodeToString(secret.getBytes())
		);

		String token = Jwts.builder()
				.claims()
				.add(claims)
				.issuer(issuer)
				.subject(subject)
				.issuedAt(createdDate)
				.id(
						UUID.randomUUID()
								.toString()
				)
				.expiration(expirationDate)
				.and()
				.signWith(Keys.hmacShaKeyFor(bytes), Jwts.SIG.HS256)
				.compact();

		// Build AuthResponseDto:
		return AuthResponseDto.builder()
				.token(token)
				.issuedAt(createdDate)
				.expiresAt(expirationDate)
				.build();
	}
}
