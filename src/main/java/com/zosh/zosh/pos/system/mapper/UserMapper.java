package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.modal.User;
import com.zosh.zosh.pos.system.payload.dto.UserDto;

public class UserMapper {
	public static UserDto toDto(User savedUser) {
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		userDto.setFullname(savedUser.getFullname());
		userDto.setEmail(savedUser.getEmail());
		userDto.setRole(savedUser.getRole());
		userDto.setPhone(savedUser.getPhone());
		userDto.setCreatedAt(savedUser.getCreatedAt());
		userDto.setUpdatedAt(savedUser.getUpdatedAt());
		userDto.setLastlogin(savedUser.getLastlogin());
		return userDto;
	}

}
