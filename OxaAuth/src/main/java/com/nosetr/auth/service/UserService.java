package com.nosetr.auth.service;

import java.util.UUID;

import com.nosetr.auth.dto.UserDto;
import com.nosetr.auth.dto.UserRegisterDto;
import com.nosetr.auth.dto.UserUpdateDto;
import com.nosetr.auth.entity.UserEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for users actions.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public interface UserService {

	/**
	 * Create new user with UserRepository.
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
	 * @param  userEntity UserEntity
	 * @return            Mono<UserEntity>
	 */
	Mono<UserDto> registerUser(UserRegisterDto userDto);

	/**
	 * Update user by ID
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  id
	 * @param  userDto
	 * @return Mono<UserEntity>
	 */
	Mono<UserEntity> update(UUID id, UserUpdateDto userDto);

	/**
	 * Update user by ID
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
	 * @param  id         UUID
	 * @param  userEntity
	 * @return            Mono<UserEntity> or Mono.empty()
	 */
	@Deprecated
	Mono<UserEntity> update(UUID id, UserEntity userEntity);

	/**
	 * Find user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id UUID
	 * @return    Mono<UserEntity>
	 */
	Mono<UserEntity> getUserById(UUID id);

	/**
	 * Find user by email
	 * 
	 * @autor        Nikolay Osetrov
	 * @since        0.1.0
	 * @param  email String
	 * @return       Mono<UserEntity>
	 */
	Mono<UserEntity> getUserByEmail(String email);

	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Get all users
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return Flux<UserEntity>
	 */
	Flux<UserEntity> getAll();

	/**
	 * Delete user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id UUID
	 * @return    Mono<Void>
	 */
	Mono<Void> deleteById(UUID id);

	/**
	 * Delete all users
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return Mono<Void>
	 */
	Mono<Void> deleteAll();

}
