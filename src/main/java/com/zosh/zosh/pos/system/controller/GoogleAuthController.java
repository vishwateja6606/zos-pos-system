package com.zosh.zosh.pos.system.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.zosh.pos.system.payload.response.AuthResponse;
import com.zosh.zosh.pos.system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class GoogleAuthController {

    private final AuthService authService;

    public GoogleAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> googleLogin(
            @RequestBody Map<String, String> request) {

        String googleToken = request.get("token");

        AuthResponse response = authService.googleLogin(googleToken);

        return ResponseEntity.ok(response);
    }
}
