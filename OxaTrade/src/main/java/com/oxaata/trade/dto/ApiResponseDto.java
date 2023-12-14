package com.oxaata.trade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * To response API messages.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponseDto {

	private Object data;
	private String message;
	private boolean error = true;

	public ApiResponseDto(Object data, String message) {
		this.data = data;
		this.message = message;
	}
}
