package com.nosetr.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.nosetr.auth.entity.NewsthemaEntity;

/**
 * Repository for newsthema-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Repository
public interface NewsthemaRepository extends R2dbcRepository<NewsthemaEntity, Long> {
	// Weitere benutzerdefinierte Abfragen können hinzugefügt werden
}
