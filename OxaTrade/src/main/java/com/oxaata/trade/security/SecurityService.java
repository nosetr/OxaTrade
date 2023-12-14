package com.oxaata.trade.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.enums.ErrorEnum;
import com.oxaata.trade.service.UserService;
import com.oxaata.trade.util.exception.AuthException;

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
public class SecurityService {

	private final UserService userService;
	private final PBFDK2Encoder passwordEncoder;

	// Values from config-file:
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Integer expirationInSeconds;
	@Value("${jwt.issuer}")
	private String issuer;

	/**
	 * Begin users login with values from {@link com.oxaata.trade.dto.AuthRequestDto
	 * AuthRequestDto} and return mapped {@link TokenDetails} as Mono.
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param  email    String
	 * @param  password String
	 * @return          Mono<TokenDetails>
	 * @see             UserService
	 * @see             TokenDetails
	 * @see             PBFDK2Encoder#matches(CharSequence, String)
	 */
	public Mono<TokenDetails> authenticate(String email, String password) {
		// Find UserEntity by email and return TokenDetails:
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
	 * @return      TokenDetails
	 */
	@SuppressWarnings("serial")
	private TokenDetails generateToken(UserEntity user) {
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
	 * @return         TokenDetails
	 * @see            TokenDetails
	 */
	private TokenDetails generateToken(Map<String, Object> claims, String subject) {

		// Set expirationDate:
		Long expirationTimeInMillis = expirationInSeconds * 1000L;
		Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);

		// Set creations date:
		Date createdDate = new Date();

		// Encode jwt.secret from config-file:
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

		// Build TokenDetails:
		return TokenDetails.builder()
				.token(token)
				.issuedAt(createdDate)
				.expiresAt(expirationDate)
				.build();
	}
}
