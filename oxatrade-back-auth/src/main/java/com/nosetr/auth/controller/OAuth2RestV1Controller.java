package com.nosetr.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

/**
 * Controller interface for authentication with OAuth2.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Tag(name = "OAuth2_V1", description = "APIs for users authentication and registration with OAuth2")
@RequestMapping("/v1/oauth2")
public interface OAuth2RestV1Controller {

	@Operation(
			summary = "OAuth2 login success", description = "Get info after users OAuth2-authentication with success.", tags = {
					"users_tag", "get_tag" }
	)
	@GetMapping("/{provider}")
	public Mono<String> loginSuccess(@PathVariable String provider, @AuthenticationPrincipal Mono<OAuth2User> oauth2User);
}
