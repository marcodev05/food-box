package com.tsk.security.config.jwt;


import com.tsk.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${jwt-secret}")
    private String SECRET_KEY;

    public String generateAccessToken(Users user) {
        Claims claims = Jwts.claims();
        claims.put("roles", user.getRoles());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("http://techdev.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.ES512, SECRET_KEY)
                .compact();
    }


    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * return false if token already expired
     * @param token
     * @return
     */
    public Boolean isTokenNonExpired(String token){
        Claims claims = extractAllClaims(token);
        Date d = claims.getExpiration();
        return d.before(new Date());
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token){
        if(!isTokenNonExpired(token)){
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
