package com.oxaata.trade.security;

import java.util.Base64;
import java.util.Date;

import com.oxaata.trade.util.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

/**
 * Verification of users token
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public class JwtHandler {

	private final String secret;

	/**
	 * Secret initialization.
	 * 
	 * @autor        Nikolay Osetrov
	 * @since        0.1.0
	 * @param secret String from config-file
	 */
	public JwtHandler(String secret) {
		this.secret = secret;
	}

	/**
	 * Begin to check the given token.
	 * 
	 * @autor              Nikolay Osetrov
	 * @since              0.1.0
	 * @param  accessToken BearerToken extracted from HttpHeaders.AUTHORIZATION
	 * @return             Mono<VerificationResult> or UnauthorizedException
	 */
	public Mono<VerificationResult> check(String accessToken) {
		return Mono.just(verify(accessToken))
				.onErrorResume(e -> Mono.error(new UnauthorizedException(e.getMessage())));
	}

	/**
	 * Verification of token if expirationDate is over
	 * 
	 * @autor        Nikolay Osetrov
	 * @since        0.1.0
	 * @param  token
	 * @return
	 */
	private VerificationResult verify(String token) {
		Claims claims = getClaimsFromToken(token);
		final Date expirationDate = claims.getExpiration();

		// if expirationDate is over, then throw exception
		if (expirationDate.before(new Date())) { throw new RuntimeException("Token expired"); }

		return new VerificationResult(claims, token);
	}

	/**
	 * Take claims from given token with secret.
	 * 
	 * @autor        Nikolay Osetrov
	 * @since        0.1.0
	 * @param  token
	 * @return
	 */
	private Claims getClaimsFromToken(String token) {

		byte[] bytes = Decoders.BASE64.decode(
				Base64.getEncoder()
						.encodeToString(secret.getBytes())
		);

		return Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(bytes))
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	/**
	 * Make result of verification.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	public static class VerificationResult {
		public Claims claims;
		public String token;

		/**
		 * Constructor for initiation.
		 * 
		 * @autor        Nikolay Osetrov
		 * @since        0.1.0
		 * @param claims
		 * @param token
		 */
		public VerificationResult(Claims claims, String token) {
			this.claims = claims;
			this.token = token;
		}
	}
}
