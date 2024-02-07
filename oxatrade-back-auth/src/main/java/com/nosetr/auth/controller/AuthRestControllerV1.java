package com.nosetr.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.dto.AuthRequestDto;
import com.nosetr.auth.dto.AuthResponseDto;
import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.service.SecurityService;
import com.nosetr.auth.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication_V1", description = "APIs for users authentication and registration")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1 {

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
	@Operation(
			summary = "Register new user", description = "Password need:\n"
					+ " * at least 8 characters and at most 100 chars,\n"
					+ " * at least one upper-case character,\n"
					+ " * at least one lower-case character,\n"
					+ " * at least one digit character,\n"
					+ " * at least one symbol (special character)\n"
					+ " * and no whitespace", tags = { "users_tag", "post_tag" }
	)
	@ApiResponses(
		{
				@ApiResponse(
						responseCode = "200", content = {
								@Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json") }
				),
				@ApiResponse(
						responseCode = "500", content = { @Content(
								schema = @Schema()
						) }
				)
		}
	)
	@PostMapping("/register")
	public Mono<UserDto> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
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
	@Operation(
			summary = "Login with email and password. Create response with token and additional info.", tags = { "users_tag",
					"post_tag" }
	)
	@ApiResponses(
		{
				@ApiResponse(
						responseCode = "200", content = {
								@Content(schema = @Schema(implementation = AuthResponseDto.class), mediaType = "application/json") }
				),
				@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
				@ApiResponse(
						responseCode = "500", content = { @Content(
								schema = @Schema()
						) }
				)
		}
	)
	@PostMapping("/login")
	public Mono<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
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
