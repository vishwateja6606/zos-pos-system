package com.zosh.zosh.pos.system.payload.dto;

import java.time.LocalDateTime;

import com.zosh.zosh.pos.system.domain.UserRole;

import lombok.Data;
@Data
public class UserDto {
	
    private Long id;
	
	
	private String fullname;
	
	
	private String email;
	
	private String password;
	
	private String phone;
	
	
	private UserRole role;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime lastlogin;

}
