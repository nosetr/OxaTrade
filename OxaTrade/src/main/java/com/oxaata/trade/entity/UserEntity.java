package com.oxaata.trade.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
	private Long id;
	private String email;
	private String password;
	private UserRoleEnum userRole;
	private String title;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@ToString.Include(name = "password")
	private String maskPassword() {
		return "********";
	}
}
