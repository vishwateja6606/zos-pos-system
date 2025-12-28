package com.zosh.zosh.pos.system.service;

import java.util.List;

import com.zosh.zosh.pos.system.payload.dto.UserDto;
import com.zosh.zosh.pos.system.payload.response.AuthResponse;

public interface AuthService {
	AuthResponse signUp(UserDto userDto);
	AuthResponse login(UserDto userDto);
	 AuthResponse googleLogin(String googleToken);
	List<UserDto> getAllUsers();
	 UserDto getUserById(Long id);
	

}
