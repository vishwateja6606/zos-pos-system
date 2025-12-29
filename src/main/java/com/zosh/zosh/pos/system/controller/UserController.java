package com.zosh.zosh.pos.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.zosh.pos.system.payload.dto.UserDto;
import com.zosh.zosh.pos.system.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getProfile(){
		return ResponseEntity.ok(userService.getLoggedInUser());
	}
	
	@PutMapping("/profile")
	public ResponseEntity<UserDto> updateProfile(UserDto dto){
		return ResponseEntity.ok(userService.updateLoggedInUser(dto));
	}
	

}
