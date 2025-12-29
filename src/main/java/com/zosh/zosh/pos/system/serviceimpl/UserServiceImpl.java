package com.zosh.zosh.pos.system.serviceimpl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zosh.zosh.pos.system.exceptions.UserExceptions;
import com.zosh.zosh.pos.system.mapper.UserMapper;
import com.zosh.zosh.pos.system.modal.User;
import com.zosh.zosh.pos.system.payload.dto.UserDto;
import com.zosh.zosh.pos.system.repository.UserRepository;
import com.zosh.zosh.pos.system.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	@Override
	public UserDto getLoggedInUser() {
		String email =SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		 User user = userRepository.findByEmail(email);

		    if (user == null) {
		        throw new UserExceptions("Logged-in user not found with email: " + email);
		    }
		
		return UserMapper.toDto(user);
	}

	@Override
	public UserDto updateLoggedInUser(UserDto userDto) {
		String email = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new UserExceptions ("Logged-in user not found with email: " + email);
		}
		if (userDto.getFullname() != null && !userDto.getFullname().isBlank()) {
	        user.setFullname(userDto.getFullname());
	    }

	    if (userDto.getPhone() != null && !userDto.getPhone().isBlank()) {
	        user.setPhone(userDto.getPhone());
	    }
		
	    return UserMapper.toDto(userRepository.save(user));

	}
	

}
