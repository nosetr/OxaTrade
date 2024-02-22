package com.nosetr.auth.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.controller.AuthRestV1Controller;
import com.nosetr.auth.dto.AuthRequestDto;
import com.nosetr.auth.dto.AuthResponseDto;
import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.service.SecurityService;
import com.nosetr.auth.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Controller for actions with users authentication.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthRestV1ControllerImpl implements AuthRestV1Controller {

	private final SecurityService securityService;
	private final UserService userService;

	/**
	 * Users registration action with requested body.
	 * TODO confirmations link on email must be send before account is enabled.
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  userDto UserDto
	 * @return         Mono<UserDto>
	 * @see            UserDto
	 */
	@Override
	public Mono<UserDto> register(@Valid UserRegisterDto userRegisterDto) {
		// Call registrations service
//		log.error("proba");
		return userService.registerUser(userRegisterDto);
	}

	/**
	 * Users login action.
	 * <p> Begin of authentication with email and password.
	 * 
	 * @autor                 Nikolay Osetrov
	 * @since                 0.1.0
	 * @param  authRequestDto AuthRequestDto
	 * @return                Mono<AuthResponseDto>
	 * @see                   AuthRequestDto
	 */
	@Override
	public Mono<AuthResponseDto> login(@Valid AuthRequestDto authRequestDto) {
		// Call authentications service
		return securityService.authenticate(authRequestDto.getEmail(), authRequestDto.getPassword())
				.flatMap(
						tokenDetails -> Mono.just(
								// Create response with token and additional info.
								AuthResponseDto.builder()
										.userId(tokenDetails.getUserId())
										.token(tokenDetails.getToken())
										.issuedAt(tokenDetails.getIssuedAt())
										.expiresAt(tokenDetails.getExpiresAt())
										.build()
						)
				);
	}
}
