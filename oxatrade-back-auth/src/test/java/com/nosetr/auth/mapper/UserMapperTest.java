package com.nosetr.auth.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.entity.UserEntity;
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

	private static LocalDateTime time;

	private static UserEntity user;

	private static UUID uuid = UUID.fromString("923e4567-e89b-12d3-a456-426655440000");

	@BeforeAll
	public static void init() {
		time = LocalDateTime.now();
		user = creatEntity();
	}

	/**
	 * UserEntity to UserDto.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.2
	 */
	@Test
	void mapping_UserEntity_to_UserDto() {
		UserDto dto = createUserDto();
		Assertions.assertEquals(dto, userMapper.map(user));
	}

	/**
	 * UserDto to UserEntity.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	@Test
	void mapping_UserDto_to_UserEntity() {
		UserDto dto = createUserDto();
		Assertions.assertEquals(user, userMapper.map(dto));
	}

	/**
	 * UserRegisterDto to UserEntity.
	 * Not equals.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	@Test
	void mapping_UserRegisterDto_to_UserEntity() {
		UserRegisterDto dto = createUserRegisterDto();

		Assertions.assertNotEquals(
				user, userMapper.map(dto)
		);

		Assertions.assertEquals(
				user, userMapper.map(dto)
						.toBuilder()
						.id(uuid)
						.userRole(UserRoleEnum.USER)
						.createdAt(time)
						.updatedAt(time)
						.build()
		);
	}

	/**
	 * Create UserRegisterDto for test.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return
	 */
	private UserRegisterDto createUserRegisterDto() {
		return new UserRegisterDto(
				"m", "Bob", "Strange", "12345§aA", "12345§aA", "proba@proba.com", false
		);
	}

	/**
	 * Create UserDto for test.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return
	 */
	private UserDto createUserDto() {
		return new UserDto(
				uuid, "proba@proba.com", "12345§aA", null, UserRoleEnum.USER, "m", "Bob", "Strange", false, false, time, time
		);
	}

	/**
	 * Create user for test.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.2
	 * @return
	 */
	private static UserEntity creatEntity() {
		UserEntity user = new UserEntity();
		user.setCreatedAt(time);
		user.setEmail("proba@proba.com");
		user.setEnabled(false);
		user.setFirstName("Bob");
		user.setId(uuid);
		user.setLastName("Strange");
		user.setPassword("12345§aA");
		user.setProvider(null);
		user.setTitle("m");
		user.setUpdatedAt(time);
		user.setUserRole(UserRoleEnum.USER);

		return user;
	}

}
