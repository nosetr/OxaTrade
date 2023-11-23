package com.oxaata.trade.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oxaata.trade.dto.UserDto;
import com.oxaata.trade.dto.UserUpdateDto;
import com.oxaata.trade.mapper.UserUpdateMapper;
import com.oxaata.trade.security.CustomPrincipal;
import com.oxaata.trade.service.UserService;

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
	private final UserUpdateMapper userMapper;

	/**
	 * User can update himself if login.
	 * TODO selfUpdate don't work
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  userDto
	 * @return
	 */
	@PostMapping("/update")
	public Mono<UserDto> selfUpdate(@RequestBody UserUpdateDto userDto, Authentication authentication) {
		
		CustomPrincipal customPrincipal = (CustomPrincipal) authentication.getPrincipal();

		return userService.getUserById(customPrincipal.getId())
				.map(userMapper::map);
	}
}
