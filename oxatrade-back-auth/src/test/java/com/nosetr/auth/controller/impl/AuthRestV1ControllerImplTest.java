/**
 * 
 */
package com.nosetr.auth.controller.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosetr.auth.dto.AuthRequestDto;
import com.nosetr.auth.dto.AuthResponseDto;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.entity.UserEntity;
import com.nosetr.auth.enums.UserRoleEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @autor Nikolay Osetrov
 * @since 0.1.4
 * @see   https://medium.com/javarevisited/spring-boot-testing-testcontainers-and-flyway-df4a71376db4
 * @see   https://medium.com/@susantamon/r2dbc-with-testcontainers-for-spring-boot-webflux-integration-test-3822b7819039
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class AuthRestV1ControllerImplTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private ObjectMapper objectMapper;

	private AuthRequestDto authRequestDto = new AuthRequestDto();
	
	/**
	 * @autor            Nikolay Osetrov
	 * @since            0.1.4
	 * @throws Exception
	 */
	@Test
	@Order(1)
	void register_new_user_without_newsletter_with_success() throws Exception {

		UserRegisterDto userRegisterDto = new UserRegisterDto();
		userRegisterDto.setFirstName("test_first_name");
		userRegisterDto.setLastName("test_last_name");
		userRegisterDto.setEmail("test@test.com");
		userRegisterDto.setPassword("12345$aA");
		userRegisterDto.setConfirmPassword("12345$aA");
		userRegisterDto.setNewsletter(false);
		userRegisterDto.setTitle("Mr.");
		String valueAsString = objectMapper.writeValueAsString(userRegisterDto);

		System.out.println("UserRegisterDtoAsString: " + valueAsString);
		//		Return with error("password" and "confirmPassword" mists
		//		because of "@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)" in UserRegisterDto): 
		//		
		//		UserRegisterDtoAsString: {
		//		"title":null,
		//		"first_name":"test_first_name",
		//		"last_name":"test_last_name",
		//		"email":"test@test.com",
		//		"newsletter":false}

		Map<String, String> user = getUserData();

		webTestClient.post()
				.uri("/v1/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						BodyInserters.fromValue(
								"{\"first_name\":\"" + user.get("first_name") + "\","
										+ "\"last_name\":\"" + user.get("last_name") + "\","
										+ "\"title\":\"" + user.get("title") + "\","
										+ "\"email\":\"" + user.get("email") + "\","
										+ "\"password\":\"" + user.get("password") + "\","
										+ "\"confirm_password\":\"" + user.get("password") + "\"}"
						)
				)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(UserEntity.class)
				.consumeWith(response -> {
					UserEntity userEntity = response.getResponseBody();
					log.info("+++ New user is created: {}", userEntity.toString());
					Assertions.assertNotNull(userEntity);

					Assertions.assertNull(userEntity.getProvider());
					Assertions.assertNull(userEntity.getPassword());

					Assertions.assertEquals(user.get("email"), userEntity.getEmail());
					Assertions.assertEquals(UserRoleEnum.USER, userEntity.getUserRole());
					Assertions.assertEquals(user.get("title"), userEntity.getTitle());
					Assertions.assertEquals(user.get("first_name"), userEntity.getFirstName());
					Assertions.assertEquals(user.get("last_name"), userEntity.getLastName());
				});

	}

	/**
	 * @autor            Nikolay Osetrov
	 * @since            0.1.4
	 * @throws Exception
	 */
	@Test
	@Order(2)
	void login_with_credentions_validation_failed() throws Exception {

		Map<String, String> user = getUserData();

		// Invalid password format
		authRequestDto.setEmail(user.get("email"));
		authRequestDto.setPassword("14578");

		var value = BodyInserters.fromValue(objectMapper.writeValueAsString(authRequestDto));

		webTestClient.post()
				.uri("/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(value)
				.exchange()
				.expectStatus()
				.isBadRequest();

		// Invalid email format
		authRequestDto.setEmail("email(at)email.com");
		authRequestDto.setPassword(user.get("password"));

		value = BodyInserters.fromValue(objectMapper.writeValueAsString(authRequestDto));

		webTestClient.post()
				.uri("/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(value)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}

	@Test
	@Order(3)
	void login_with_invalid_credentions_without_success() throws Exception {

		Map<String, String> user = getUserData();

		// Invalid email
		authRequestDto.setEmail("invalid@test.com");
		authRequestDto.setPassword(user.get("password"));
		var value = BodyInserters.fromValue(objectMapper.writeValueAsString(authRequestDto));

		webTestClient.post()
				.uri("/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(value)
				.exchange()
				.expectStatus()
				.is5xxServerError();

		// Invalid password
		authRequestDto.setEmail(user.get("email"));
		authRequestDto.setPassword("invalid" + user.get("password"));
		value = BodyInserters.fromValue(objectMapper.writeValueAsString(authRequestDto));

		webTestClient.post()
				.uri("/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(value)
				.exchange()
				.expectStatus()
				.is5xxServerError();

		// Invalid password and email
		authRequestDto.setEmail("invalid@test.com");
		authRequestDto.setPassword("invalid" + user.get("password"));
		value = BodyInserters.fromValue(objectMapper.writeValueAsString(authRequestDto));

		webTestClient.post()
				.uri("/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(value)
				.exchange()
				.expectStatus()
				.is5xxServerError();
	}

	/**
	 * @autor            Nikolay Osetrov
	 * @since            0.1.4
	 * @throws Exception
	 */
	@Test
	@Order(4)
	void login_with_email_and_pass_with_success() throws Exception {

		Map<String, String> user = getUserData();

		authRequestDto.setEmail(user.get("email"));
		authRequestDto.setPassword(user.get("password"));

		String valueAsString = objectMapper.writeValueAsString(authRequestDto);

		webTestClient.post()
				.uri("/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						BodyInserters.fromValue(valueAsString)
				)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(AuthResponseDto.class)
				.consumeWith(response -> {
					AuthResponseDto userEntity = response.getResponseBody();
					log.info("+++ User is sign in: {}", userEntity.toString());
					Assertions.assertNotNull(userEntity);
					Assertions.assertNotNull(userEntity.getToken());
					Assertions.assertInstanceOf(UUID.class, userEntity.getUserId());
					Assertions.assertInstanceOf(String.class, userEntity.getToken());
					Assertions.assertInstanceOf(Date.class, userEntity.getExpiresAt());
					Assertions.assertInstanceOf(Date.class, userEntity.getIssuedAt());
				});
	}

	/**
	 * Set default users data as HashMap for tests.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.4
	 * @return
	 */
	private Map<String, String> getUserData() {
		Map<String, String> userMap = new HashMap<>();
		userMap.put("title", "Mr.");
		userMap.put("first_name", "test_first_name");
		userMap.put("last_name", "test_last_name");
		userMap.put("email", "test@test.com");
		userMap.put("password", "12345$aA");
		return userMap;
	}

}
