package com.hackathon_iste.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final String SECRET =
            "my-super-secret-key-for-hackathon-project-123456";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24; // 1 day

    private static final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // 🔹 TOKEN GENERATE
    public static String generateToken(Long userId, String email,String name) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("name",name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 TOKEN PARSE
    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Long getUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }
}
