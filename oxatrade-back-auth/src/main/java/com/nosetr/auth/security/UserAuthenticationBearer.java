package com.nosetr.auth.security;

import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

/**
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public class UserAuthenticationBearer {

	/**
	 * Build users authentication.
	 * 
	 * @autor                     Nikolay Osetrov
	 * @since                     0.1.0
	 * @param  verificationResult JwtHandler.VerificationResult
	 * @return                    Mono<Authentication>
	 * @see                       org.springframework.security.core.authority.SimpleGrantedAuthority.SimpleGrantedAuthority
	 */
	public static Mono<Authentication> create(JwtHandler.VerificationResult verificationResult) {
		// Get values from claims:
		Claims claims = verificationResult.claims;
		String subject = claims.getSubject();

		String role = claims.get("role", String.class);
		String email = claims.get("email", String.class);

		// Stores a representation of an authority granted to the Authentication object.
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

		UUID principalId = UUID.fromString(subject);
		CustomPrincipal principal = new CustomPrincipal(principalId, email);

		return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(principal, null, authorities));
	}
}
