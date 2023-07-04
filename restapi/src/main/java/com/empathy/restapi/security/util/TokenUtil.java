package com.empathy.restapi.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud";
    private final static Long ACCESS_TOKEN_EXPIRATION = 86400000L;

    public static String createToken(String username, String email) {
        long exp = ACCESS_TOKEN_EXPIRATION * 1000;
        Date expiration = new Date(System.currentTimeMillis() + exp);

        Map<String, Object> extra = new HashMap<>();
        extra.put("username", username);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiration)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
        } catch (Exception e) {
            return null;
        }

    }
}
