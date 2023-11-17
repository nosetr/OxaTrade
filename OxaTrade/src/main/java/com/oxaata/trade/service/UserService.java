package com.oxaata.trade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for users
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Get all users
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return Flux<UserEntity>
	 */
	public Flux<UserEntity> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Find user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id Long
	 * @return    Mono<UserEntity>
	 */
	public Mono<UserEntity> findById(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * Find user by email
	 * 
	 * @autor        Nikolay Osetrov
	 * @since        0.1.0
	 * @param  email String
	 * @return       Mono<UserEntity>
	 */
	public Mono<UserEntity> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Update user by ID
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
	 * @param  id         Long
	 * @param  userEntity
	 * @return            Mono<UserEntity> or Mono.empty()
	 */
	public Mono<UserEntity> update(Long id, UserEntity userEntity) {
		return userRepository.findById(id)
				.map(Optional::of)
				.defaultIfEmpty(Optional.empty())
				.flatMap(optionalUser -> {
					if (optionalUser.isPresent()) {
						userEntity.setId(id);
						return userRepository.save(userEntity);
					}
					// if no user exists
					return Mono.empty();
				});
	}

	/**
	 * Delete user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id Long
	 * @return    Mono<Void>
	 */
	public Mono<Void> deleteById(Long id) {
		return userRepository.deleteById(id);
	}

	/**
	 * Delete all users
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return Mono<Void>
	 */
	public Mono<Void> deleteAll() {
		return userRepository.deleteAll();
	}

}
