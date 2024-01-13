package com.nosetr.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserUpdateDto;
import com.nosetr.auth.mapper.UserMapper;
import com.nosetr.auth.mapper.UserUpdateMapper;
import com.nosetr.auth.security.CustomPrincipal;
import com.nosetr.auth.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Controller for actions with users profile.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class UserProfileControllerV1 {

	private final UserService userService;
	private final UserMapper userMapper;
	private final UserUpdateMapper userUpdateMapper;

	/**
	 * User can update himself if login.
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  userDto
	 * @return
	 */
	@PostMapping("/update")
	public Mono<UserDto> selfUpdate(@Valid @RequestBody UserUpdateDto userDto, Authentication authentication) {
		
		CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

		return userService.update(customPrincipal.getId(), userDto)
				.map(userUpdateMapper::map);
	}

	/**
	 * Get information about himself when user is on login.
	 * 
	 * @autor                 Nikolay Osetrov
	 * @since                 0.1.0
	 * @param  authentication
	 * @return
	 */
	//	@PreAuthorize("hasAnyRole('USER')") // TODO make role
	@GetMapping("/info")
	public Mono<UserDto> getUserInfo(Authentication authentication) {
		CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

		return userService.getUserById(customPrincipal.getId())
				.map(userMapper::map);
	}
}