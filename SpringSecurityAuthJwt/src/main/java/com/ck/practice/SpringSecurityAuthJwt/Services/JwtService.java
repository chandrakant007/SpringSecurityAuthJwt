package com.ck.practice.SpringSecurityAuthJwt.Services;

import java.security.Key; // Use Java Security Key, NOT Hibernate Key
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ck.practice.SpringSecurityAuthJwt.proxy.Dummy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//JwtService.java
@Service
public class JwtService {

 @Value("${security.jwt.secret}")
 private String secret;

 @Value("${security.jwt.expiration-ms:3600000}") // 1 hour
 private long expirationMs;
 
 private Key getSigningKey() {
     byte[] keyBytes = Decoders.BASE64.decode(secret);
     return Keys.hmacShaKeyFor(keyBytes);
 }

 // JWT grant: issue token after successful authentication
 public String generateToken(UserDetails userDetails) {
     Date now = new Date();
     Date expiry = new Date(now.getTime() + expirationMs);

     return Jwts.builder()
             .setSubject(userDetails.getUsername())
             .claim("roles", userDetails.getAuthorities()
                     .stream()
                     .map(GrantedAuthority::getAuthority)
                     .toList())
             .setIssuedAt(now)
             .setExpiration(expiry)
             .signWith(getSigningKey(), SignatureAlgorithm.HS256)
             .compact();
 }

 public String extractUsername(String token) {
     return extractAllClaims(token).getSubject();
 }

 public boolean isTokenValid(String token, UserDetails userDetails) {
     String username = extractUsername(token);
     return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
 }

 private boolean isTokenExpired(String token) {
     Date expiration = extractAllClaims(token).getExpiration();
     return expiration.before(new Date());
 }

 private Claims extractAllClaims(String token) {
     return Jwts.parserBuilder()
             .setSigningKey(getSigningKey())
             .build()
             .parseClaimsJws(token)
             .getBody();
 }

 
}
