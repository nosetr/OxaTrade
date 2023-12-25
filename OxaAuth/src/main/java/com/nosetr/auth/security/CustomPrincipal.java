package com.nosetr.auth.security;

import java.security.Principal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO to return not only name, but also id of user
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPrincipal implements Principal {
	private UUID id;
	private String name; // as email
}
