/**
 * 
 */
package com.nosetr.auth.controller.impl;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @autor Nikolay Osetrov
 * @since 0.1.4
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthRestV1ControllerImplTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
