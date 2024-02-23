package com.nosetr.auth.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nosetr.auth.entity.UserEntity;
import com.nosetr.auth.service.UserService;
import com.nosetr.library.enums.ErrorEnum;
import com.nosetr.library.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Manager for users validation
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

	private final UserService userService;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		// To return not only name, but also id of user
		CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();

		return userService.getUserById(principal.getId()) // find one
				.filter(UserEntity::isEnabled) // only if active
				.switchIfEmpty(Mono.error(new UnauthorizedException(ErrorEnum.USER_ACCOUNT_IS_DISABLED))) // if no user founded
				.map(user -> authentication);
	}
}
