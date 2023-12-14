package com.oxaata.trade.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.oxaata.trade.enums.OAuth2ProvidersEnum;
import com.oxaata.trade.enums.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity for user-table
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserEntity {
	@Id
	private UUID id;
	private String email;
	private String password;
	private OAuth2ProvidersEnum provider; // 'google' or 'facebook', etc.
	private UserRoleEnum userRole;
	private String title;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
//	public UserEntity() {
//		this.id = UUID.randomUUID();
//	}

	@ToString.Include(name = "password")
	private String maskPassword() {
		return "********";
	}
}
