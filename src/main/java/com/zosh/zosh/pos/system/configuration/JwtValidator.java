package com.zosh.zosh.pos.system.configuration;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                SecretKey key =
                        Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                // ✅ EMAIL FROM SUBJECT
                String email = claims.getSubject();

                // ✅ AUTHORITIES FROM CLAIM
                String authorities =
                        claims.get("authorities", String.class);

                List<GrantedAuthority> auths =
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,   // 👈 principal
                                null,
                                auths
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
