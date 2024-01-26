package com.nosetr.auth.dto;

import java.time.LocalDateTime;

import com.nosetr.auth.util.annotation.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for newsletters.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Data
@Builder(toBuilder = true)
public class NewsletterDto {

	@NotBlank(message = "{validation.field.NotBlank}")
	@ValidEmail
	private String email;
	private boolean enabled;
	private LocalDateTime lastUpdate;

	/*
	 * From newsthema-table:
	 */
	private String themaName;
	private String themaMemo;

}
