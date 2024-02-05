package com.nosetr.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.nosetr.auth.entity.NewsthemaEntity;

import reactor.core.publisher.Mono;

/**
 * Repository for newsthema-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Repository
public interface NewsthemaRepository extends R2dbcRepository<NewsthemaEntity, Long> {

	Mono<NewsthemaEntity> findOneById(Long id);

//	Flux<NewsthemaEntity> findNewsthemesByNewsletterId(UUID emailId);
}
