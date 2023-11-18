package com.oxaata.trade.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.oxaata.trade.entity.UserEntity;

import reactor.core.publisher.Mono;

/**
 * Reactive repository for users table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   https://spring.io/guides/gs/accessing-data-r2dbc/
 */
@Repository
public interface UserRepository extends R2dbcRepository<UserEntity, Long> {

	//	@Query("SELECT * FROM customer WHERE last_name = :lastname")
	//  Flux<Customer> findByLastName(String lastName);

	Mono<UserEntity> findByEmail(String username);
}
