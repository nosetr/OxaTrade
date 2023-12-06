package com.oxaata.trade.service;

import com.oxaata.trade.dto.UserUpdateDto;
import com.oxaata.trade.entity.UserEntity;

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
	Mono<UserEntity> registerUser(UserEntity userEntity);

	/**
	 * Update user by ID
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  id
	 * @param  userDto
	 * @return Mono<UserEntity>
	 */
	Mono<UserEntity> update(Long id, UserUpdateDto userDto);

	/**
	 * Update user by ID
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
	 * @param  id         Long
	 * @param  userEntity
	 * @return            Mono<UserEntity> or Mono.empty()
	 */
	@Deprecated
	Mono<UserEntity> update(Long id, UserEntity userEntity);

	/**
	 * Find user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id Long
	 * @return    Mono<UserEntity>
	 */
	Mono<UserEntity> getUserById(Long id);

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
	 * @param  id Long
	 * @return    Mono<Void>
	 */
	Mono<Void> deleteById(Long id);

	/**
	 * Delete all users
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return Mono<Void>
	 */
	Mono<Void> deleteAll();

}
