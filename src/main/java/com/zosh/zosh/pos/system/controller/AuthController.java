package com.zosh.zosh.pos.system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.zosh.pos.system.exceptions.UserExceptions;
import com.zosh.zosh.pos.system.payload.dto.UserDto;
import com.zosh.zosh.pos.system.payload.response.AuthResponse;
import com.zosh.zosh.pos.system.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody UserDto userDto) throws UserExceptions{
		return ResponseEntity.ok(authService.signUp(userDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody UserDto userDto) throws UserExceptions{
		return ResponseEntity.ok(authService.login(userDto));
	}
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
	    return ResponseEntity.ok(authService.getAllUsers());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
	    return ResponseEntity.ok(authService.getUserById(id));
	}

}
