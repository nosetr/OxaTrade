package com.nosetr.auth.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for newsletter-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("newsletter")
public class NewsletterEntity {

	@Id
	private String email;
	private boolean enabled;
	private LocalDateTime lastUpdate;
}
