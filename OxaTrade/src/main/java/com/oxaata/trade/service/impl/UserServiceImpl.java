package com.oxaata.trade.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oxaata.trade.dto.UserUpdateDto;
import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.enums.ErrorEnum;
import com.oxaata.trade.enums.UserRoleEnum;
import com.oxaata.trade.mapper.UserUpdateMapper;
import com.oxaata.trade.repository.UserRepository;
import com.oxaata.trade.security.PBFDK2Encoder;
import com.oxaata.trade.service.UserService;
import com.oxaata.trade.util.exception.EntityAlreadyExistsException;
import com.oxaata.trade.util.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of UserService for users actions.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   PBFDK2Encoder
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final PBFDK2Encoder passwordEncoder;
	private final UserUpdateMapper userUpdateMapper;

	/**
	 * Create new user with UserRepository.
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
	 * @param  userEntity UserEntity
	 * @return            Mono<UserEntity>
	 * @see               PBFDK2Encoder
	 */
	@Override
	public Mono<UserEntity> registerUser(UserEntity userEntity) {
		String email = userEntity.getEmail();
		// Check if email is already in use:
		return userRepository.findByEmail(email)
				.map(Optional::of)
				.defaultIfEmpty(Optional.empty())
				.flatMap(optionalUser -> {
					// Exception if user with email founded:
					if (optionalUser.isPresent()) {
						return Mono.error(
								new EntityAlreadyExistsException(ErrorEnum.USER_WITH_EMAIL_ALREADY_EXISTS, email)
						);
					}
					// Create new user:
					return userRepository.save(
							// Build special fields of user with default values.
							userEntity.toBuilder()
									// Password encode
									.password(passwordEncoder.encode(userEntity.getPassword()))
									.userRole(UserRoleEnum.USER)
									.enabled(true) // TODO make false when email verifications system worked.
									.createdAt(LocalDateTime.now())
									.updatedAt(LocalDateTime.now())
									.build()
					)
							.doOnSuccess(u -> {
								// Make log about new users registration.
								log.info("IN registerUser - user: {} created", u);
							});
				});
	}

	/**
	 * Update user by ID
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param  id
	 * @param  userDto
	 * @return
	 */
	@Override
	public Mono<UserEntity> update(Long id, UserUpdateDto userDto) {

		return userRepository.findById(id)
				// only if active:
				.filter(UserEntity::isEnabled)
				.flatMap(
						found -> userRepository.save(
								userUpdateMapper.updateUserFromDto(
										userDto, found.toBuilder()
												.updatedAt(LocalDateTime.now())
												.build()
								)
						)
				)
				// if no user founded:
				.switchIfEmpty(Mono.error(new UnauthorizedException(ErrorEnum.USER_ACCOUNT_IS_DISABLED)));
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
	@Override
	@Deprecated
	public Mono<UserEntity> update(Long id, UserEntity userEntity) {
		return userRepository.findById(id)
				.map(Optional::of)
				.defaultIfEmpty(Optional.empty())
				.flatMap(optionalUser -> {
					if (optionalUser.isPresent()) {
						// Set defaults
						userEntity.toBuilder()
								.id(id)
								.userRole(
										optionalUser.get()
												.getUserRole()
								)
								.createdAt(
										optionalUser.get()
												.getCreatedAt()
								)
								.updatedAt(LocalDateTime.now())
								.build();
						System.out.println(userEntity);
						return userRepository
								.save(userEntity);
					}
					// if no user exists
					return Mono.empty();
				});
	}

	/**
	 * Find user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id Long
	 * @return    Mono<UserEntity>
	 */
	@Override
	public Mono<UserEntity> getUserById(Long id) {
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
	@Override
	public Mono<UserEntity> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Get all users
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return Flux<UserEntity>
	 */
	@Override
	public Flux<UserEntity> getAll() { return userRepository.findAll(); }

	/**
	 * Delete user by ID
	 * 
	 * @autor     Nikolay Osetrov
	 * @since     0.1.0
	 * @param  id Long
	 * @return    Mono<Void>
	 */
	@Override
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
	@Override
	public Mono<Void> deleteAll() {
		return userRepository.deleteAll();
	}

}
