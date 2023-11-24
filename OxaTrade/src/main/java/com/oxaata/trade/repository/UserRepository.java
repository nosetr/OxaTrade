package com.oxaata.trade.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oxaata.trade.entity.UserEntity;
import com.oxaata.trade.enums.UserRoleEnum;

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

	Mono<UserEntity> findByEmail(String email);

	/**
	 * A custom query for partial updates.
	 * <p>In fact, JPA defines two annotations, @Modifying and @Query, that allow us
	 * to write our update statement explicitly.
	 * <p><b>We can now tell our application how to behave during an update without
	 * leaving the burden on the ORM.</b>
	 * <p>Now we can rewrite our update method: {@code repo.updateUserRole(id, userRole);}
	 * 
	 * @autor          Nikolay Osetrov
	 * @since          0.1.0
	 * @param id
	 * @param userRole
	 * @see   https://www.baeldung.com/spring-data-partial-update#custom-query
	 */
	@Modifying
	@Query("UPDATE users u SET u.user_role = :userRole where u.id = :id")
	void updateUserRole(@Param(value = "id") long id, @Param(value = "userRole") UserRoleEnum userRole);
}
