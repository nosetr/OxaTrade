package com.nosetr.auth.controller.impl;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.controller.ProfileRestV1Controller;
import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserUpdateDto;
import com.nosetr.auth.mapper.UserMapper;
import com.nosetr.auth.security.CustomPrincipal;
import com.nosetr.auth.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Controller implementation for actions with users profile.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@RestController
@RequiredArgsConstructor
public class ProfileRestV1ControllerImpl implements ProfileRestV1Controller {

	private final UserService userService;
	private final UserMapper userMapper;

	/**
	 * User can update himself if login.
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  userDto
	 * @return
	 */
	@Override
	public Mono<UserDto> selfUpdate(@Valid UserUpdateDto userDto, Authentication authentication) {

		CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

		return userService.update(customPrincipal.getId(), userDto)
				.map(userMapper::map);
	}

	/**
	 * Get information about himself when user is on login.
	 * 
	 * @autor                 Nikolay Osetrov
	 * @since                 0.1.0
	 * @param  authentication
	 * @return
	 */
	@Override
	public Mono<UserDto> getUserInfo(Authentication authentication) {
		CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

		return userService.getUserById(customPrincipal.getId())
				.map(userMapper::map);
	}
}
