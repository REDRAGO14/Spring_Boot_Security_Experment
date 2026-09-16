package com.example.student_api.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private final String SECRET = "eX9+Z1P2qLmN4vRw8kY3tA6sD0fG1hJ5kL7mNpQrStUvWxYz0aBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUvWw==";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long ExpirationTime = 1000*60*60; //1hr
    public String generateToken(String username){
       return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
