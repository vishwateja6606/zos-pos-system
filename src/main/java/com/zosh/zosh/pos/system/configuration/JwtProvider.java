package com.zosh.zosh.pos.system.configuration;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.zosh.zosh.pos.system.modal.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtProvider {

    private static final SecretKey key =
            Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    // NORMAL LOGIN / SIGNUP
    public String generateToken(Authentication authentication) {

        String email = authentication.getName();

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(email)
                .claim("authorities", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JwtConstant.JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    // GOOGLE LOGIN
    public String generateTokenFromUser(User user) {

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("authorities", user.getRole().name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JwtConstant.JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    public static String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static String extractAuthorities(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("authorities", String.class);
    }
}
