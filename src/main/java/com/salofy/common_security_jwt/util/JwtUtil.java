package com.salofy.common_security_jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret"; // Must be 32+ chars for HS256

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public Jws<Claims> validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token); // Throws exception if invalid
    }

    public String getUsername(String token) {
        return validateToken(token).getBody().getSubject();
    }
}
