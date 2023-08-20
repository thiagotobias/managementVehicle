package com.tobias.managementVehicle.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tobias.managementVehicle.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${management.auth.secret-key}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Object> extraClaims) {


        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] bytesArr = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytesArr);

    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        Date date = extractExpirationTime(token);
        boolean isTokenExpired = date.before(new Date());
        return userDetails.getUsername().equals(username) && !isTokenExpired;
    }

    private Date extractExpirationTime(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
