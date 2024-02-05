package com.nosetr.auth.service;

import com.nosetr.auth.security.TokenDetails;

import reactor.core.publisher.Mono;

/**
 * Make handling with authentication and tokens generation.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public interface SecurityService {

	/**
	 * Begin users login with values from {@link com.nosetr.auth.dto.AuthRequestDto
	 * AuthRequestDto} and return mapped {@link TokenDetails} as Mono.
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param  email    String
	 * @param  password String
	 * @return          Mono<TokenDetails>
	 */
	Mono<TokenDetails> authenticate(String email, String password);
}
