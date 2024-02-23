/**
 * 
 */
package com.nosetr.auth.controller.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.entity.UserEntity;

/**
 * @autor Nikolay Osetrov
 * @since 0.1.4
 */
@ActiveProfiles("test")
@SpringBootTest
//@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class AuthRestV1ControllerImplTest {

	@Autowired
	//	private MockMvc mockMvc;
	private WebTestClient webTestClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void register_new_user_with_success_without_newsletter() throws Exception {
		UserRegisterDto userRegisterDto = new UserRegisterDto();
		userRegisterDto.setFirstName("test_first_name");
		userRegisterDto.setLastName("test_last_name");
		userRegisterDto.setEmail("test@test.com");
		userRegisterDto.setPassword("12345$aA");
		userRegisterDto.setConfirmPassword("12345$aA");
		userRegisterDto.setNewsletter(false);

		String valueAsString = objectMapper.writeValueAsString(userRegisterDto);

		System.out.println("**********************************************");
		System.out.println("**********************************************");
		System.out.println("**********************************************");

		System.out.println("UserRegisterDtoAsString: " + valueAsString);
		//		Return with error(password and confirmPassword mists
		//		because of "@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)" in UserRegisterDto): 
		//		
		//		UserRegisterDtoAsString: {
		//		"title":null,
		//		"first_name":"test_first_name",
		//		"last_name":"test_last_name",
		//		"email":"test@test.com",
		//		"newsletter":false}

		System.out.println("**********************************************");
		System.out.println("**********************************************");
		System.out.println("**********************************************");

		//		MvcResult mvcResult = mockMvc
		//				.perform(
		//						MockMvcRequestBuilders.post("/register")
		//								.contentType(MediaType.APPLICATION_JSON)
		//								.content(valueAsString)
		//				)
		//				.andReturn();
		//		
		//		MockHttpServletResponse jsonHttpServletResponse = mvcResult.getResponse();
		//		
		//		UserEntity userEntity = objectMapper.readValue(jsonHttpServletResponse.getContentAsString(), UserEntity.class);		
		//
		//    Assertions.assertEquals(200, jsonHttpServletResponse.getStatus());
		//    Assertions.assertEquals(userRegisterDto.getFirstName(), userEntity.getFirstName());

		webTestClient.post()
				.uri("/api/v1/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(valueAsString))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(UserEntity.class)
				.consumeWith(response -> {
					UserEntity userEntity = response.getResponseBody();
					Assertions.assertNotNull(userEntity);
					Assertions.assertEquals(userRegisterDto.getFirstName(), userEntity.getFirstName());
				});

	}

}
