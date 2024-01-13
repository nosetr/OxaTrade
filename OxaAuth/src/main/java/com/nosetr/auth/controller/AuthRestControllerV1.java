package com.nosetr.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.dto.AuthRequestDto;
import com.nosetr.auth.dto.AuthResponseDto;
import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.entity.UserEntity;
import com.nosetr.auth.mapper.UserMapper;
import com.nosetr.auth.security.SecurityService;
import com.nosetr.auth.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Controller for actions with users authentication.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1 {

	private final SecurityService securityService;
	private final UserService userService;
	private final UserMapper userMapper;

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
	@PostMapping("/register")
	public Mono<UserDto> register(@Valid @RequestBody UserDto userDto) {
		// Map UserDto with UserEntity
		UserEntity entity = userMapper.map(userDto);
		// Call registrations service
		return userService.registerUser(entity)
				.map(userMapper::map);
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
	@PostMapping("/login")
	public Mono<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
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