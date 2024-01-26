package com.nosetr.auth.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.entity.UserEntity;
import com.nosetr.auth.enums.OAuth2ProvidersEnum;
import com.nosetr.auth.enums.UserRoleEnum;

/**
 * To test UserMapper
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@DisplayName("Test class for UserMapper")
@ExtendWith(MockitoExtension.class)
class UserMapperTest {

	@Spy
	private final UserMapper userMapper = new UserMapperImpl();

	private LocalDateTime time;

	@BeforeEach
	public void init() {
		time = LocalDateTime.now();
	}

	/**
	 * UserEntity to UserDto.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.2
	 */
	@Test
	void mapping_UserEntity_to_UserDto() {

		UserEntity user = creatEntity();

		UserDto dto = createUserDto();

		Assertions.assertEquals(dto, userMapper.map(user));
	}

	/**
	 * Create user for test.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.2
	 * @return
	 */
	private UserEntity creatEntity() {
		UserEntity user = new UserEntity();
		user.setCreatedAt(time);
		user.setEmail("proba@proba.com");
		user.setEnabled(false);
		user.setFirstName("Bob");
		user.setId(UUID.fromString("923e4567-e89b-12d3-a456-426655440000"));
		user.setLastName("Strange");
		user.setPassword("123456");
		user.setProvider(OAuth2ProvidersEnum.GOOGLE);
		user.setTitle("m");
		user.setUpdatedAt(time);
		user.setUserRole(UserRoleEnum.USER);

		return user;
	}

	/**
	 * Create DTO for test.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return
	 */
	private UserDto createUserDto() {
		return new UserDto(
				UUID.fromString(
						"923e4567-e89b-12d3-a456-426655440000"
				), "proba@proba.com", "123456", null, UserRoleEnum.USER, "m", "Bob", "Strange", false, false, time, time
		);
	}

}
