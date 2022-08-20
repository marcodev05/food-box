package com.tsk.security.config.jwt;


import com.tsk.domain.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    //private static final long EXPIRE_DURATION = 4 * 60 * 60 * 1000; // 4 hours

    private static final long EXPIRE_DURATION = 2 * 60 * 60 * 1000; // 2 hours

    @Value("${jwt-secret}")
    private String SECRET_KEY;

    public String generateAccessToken(UserEntity user) {
        Claims claims = Jwts.claims();
        claims.put("userId", user.getUserId());
        claims.put("roles", user.getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuer("http://techdev.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * return true if token already expired
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token){
        Claims claims = extractAllClaims(token);
        Date d = claims.getExpiration();
        return d.before(new Date());
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token) {
       if (isTokenExpired(token) == true) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public String getEmailFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

}
