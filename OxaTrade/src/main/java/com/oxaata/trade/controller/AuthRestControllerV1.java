package com.oxaata.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1 {

	@Autowired
	UserService userService;

	/**
	 * Get users info by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id Long
	 * @return    Mono<UserEntity>
	 */
	@GetMapping("/info/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<UserEntity> getUserInfo(@PathVariable("id") Long id) {
		return userService.findById(id);
	}
}
