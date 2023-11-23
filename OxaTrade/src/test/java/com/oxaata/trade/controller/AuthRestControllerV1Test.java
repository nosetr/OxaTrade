package com.oxaata.trade.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxaata.trade.dto.UserDto;
import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.mapper.UserMapper;
import com.oxaata.trade.service.UserService;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import reactor.core.publisher.Mono;

/**
 * Tests for AuthRestControllerV1
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@WebFluxTest(AuthRestControllerV1.class)
class AuthRestControllerV1Test {

	@Mock
	private UserService userService;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private AuthRestControllerV1 authRestControllerV1;

	private final WebTestClient webTestClient;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = validatorFactory.getValidator();

	public AuthRestControllerV1Test(WebTestClient.Builder webTestClientBuilder) {
		this.webTestClient = webTestClientBuilder
				.controller(authRestControllerV1)
				.build();
	}

	@Test
	public void createNewUserWithSuccess() {
		// Mock data
		UserDto userDto = new UserDto();
		userDto.setFirstName("Fritz");
		userDto.setLastName("Hahn");
		userDto.setEmail("proba@proba.com");
		userDto.setPassword("12345$aA");
		userDto.setConfirmPassword("12345$aA");

		// Mock mapping
		when(userMapper.map(userDto)).thenReturn(new UserEntity());

		// Mock service response
		when(userService.registerUser(new UserEntity())).thenReturn(Mono.just(new UserEntity()));

		// Perform the request and verify the response
		//		webTestClient.post()
		//				.uri("/register")
		//				.bodyValue(userDto)
		//				.exchange()
		//				.expectStatus()
		//				.isOk()
		//				.expectBody(UserDto.class)
		//				.value(Objects::nonNull, value -> {
		//					// TODO add additional assertions on the returned UserDto
		//				});
	}

}
