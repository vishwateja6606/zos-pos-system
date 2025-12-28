package com.zosh.zosh.pos.system.serviceimpl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zosh.zosh.pos.system.modal.User;
import com.zosh.zosh.pos.system.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomerUserImplementation implements UserDetailsService{
	private final UserRepository userRepository;
	
	@Override
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user =userRepository.findByEmail(username);
		
	
	if(user==null) {
		throw new UsernameNotFoundException("user not found");
	}
	GrantedAuthority authority= new SimpleGrantedAuthority(
			user.getRole().toString()
			);
	Collection<GrantedAuthority> authorities =
	        java.util.Collections.singletonList(authority);
	return new org.springframework.security.core.userdetails.User(
	        user.getEmail(),
	        user.getPassword(),
	        authorities
	);

	}
}
