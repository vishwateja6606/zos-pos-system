package com.zosh.zosh.pos.system.serviceimpl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zosh.zosh.pos.system.configuration.JwtProvider;
import com.zosh.zosh.pos.system.domain.UserRole;
import com.zosh.zosh.pos.system.exceptions.UserExceptions;
import com.zosh.zosh.pos.system.mapper.UserMapper;
import com.zosh.zosh.pos.system.modal.User;
import com.zosh.zosh.pos.system.payload.dto.UserDto;
import com.zosh.zosh.pos.system.payload.response.AuthResponse;
import com.zosh.zosh.pos.system.repository.UserRepository;
import com.zosh.zosh.pos.system.service.AuthService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomerUserImplementation cimpl;
    private final GoogleAuthService googleAuthService;

    // ================= SIGN UP =================
    @Override
    public AuthResponse signUp(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new UserExceptions("Email already exists");
        }

        if (UserRole.ROLE_ADMIN.equals(userDto.getRole())) {
            throw new UserExceptions("ROLE_ADMIN is not allowed");
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        user.setFullname(userDto.getFullname());
        user.setPhone(userDto.getPhone());

        // ✅ Backend-controlled timestamps
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setLastlogin(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        savedUser.getEmail(),
                        null,
                        cimpl.loadUserByUsername(savedUser.getEmail()).getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Registered successfully");
        response.setUserDto(UserMapper.toDto(savedUser));

        return response;
    }

    // ================= LOGIN =================
    @Override
    public AuthResponse login(UserDto userDto) {

        Authentication authentication =
                authenticate(userDto.getEmail(), userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(userDto.getEmail());
        user.setLastlogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Login successful");
        response.setUserDto(UserMapper.toDto(user));

        return response;
    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = cimpl.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserExceptions("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    // ================= GOOGLE LOGIN =================
    @Override
    public AuthResponse googleLogin(String googleToken) {

        var payload = googleAuthService.verifyToken(googleToken);

        String email = payload.getEmail();
        String fullName = (String) payload.get("name");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setFullname(fullName);
            user.setRole(UserRole.ROLE_USER);

            // 🔐 Dummy password (never used)
            user.setPassword(passwordEncoder.encode("GOOGLE_LOGIN"));

            user.setCreatedAt(LocalDateTime.now());
        }

        user.setLastlogin(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        String jwt = jwtProvider.generateTokenFromUser(savedUser);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Google login successful");
        response.setUserDto(UserMapper.toDto(savedUser));

        return response;
    }

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
		  .stream()
		  .map(UserMapper::toDto)
		  .toList();
		
	}

	@Override
	public UserDto getUserById(Long id) {
		User user =userRepository.findById(id)
				.orElseThrow(() -> new UserExceptions("User not found with id: " + id));
		return UserMapper.toDto(user);
	}
}

