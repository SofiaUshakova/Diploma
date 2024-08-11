package com.example.diploma.SecurityJWT;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Duration;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
@Component
public class Token {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiry}")

    private Duration expiry;
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + expiry.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUserNameFromToken(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public List<String> getRolesFromToken(String token){
        return getAllClaimsFromToken(token).get("roles", List.class);
    }
}
