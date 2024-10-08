package com.instagram.instagram_social.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){
        
        @SuppressWarnings("deprecation")
        String jwt  = Jwts.builder()
                    .setIssuer("CodeWithZosh").setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 86400000))
                    .claim("email", auth.getName())
                    .signWith(key)
                    .compact();

        return jwt;
    }

    public static String getEmailFromJWTToken(String jwt){
        //Bearer token
        jwt =  jwt.substring(7);
        @SuppressWarnings("deprecation")
        Claims claims = Jwts.parser()
                            .setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));
        return email;

    }
}