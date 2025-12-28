package com.zosh.zosh.pos.system.configuration;

public class JwtConstant {

    // Must be at least 256 bits (32+ characters long) for HS256
    public static final String JWT_SECRET =
            "ThisIsAVeryLongSecretKeyForJwtEncryption1234567890";

    // Token validity (optional, useful for JWT generator)
    public static final long JWT_EXPIRATION = 1000 * 60 * 60 * 10; // 10 hours

    // Authorization header prefix
    public static final String TOKEN_PREFIX = "Bearer ";

    // Header name
    public static final String AUTH_HEADER = "Authorization";
}
