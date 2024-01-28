package com.nosetr.auth.dto;

import java.util.Set;

import com.nosetr.auth.entity.NewsletterEntity;

import lombok.Builder;
import lombok.Data;

/**
 * DTO for news theme.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Data
@Builder
public class NewsthemaDto {

	private Long id;
	private String themaName;
	private String memo;
	private Set<NewsletterEntity> emails;
}
