package com.example.springbootjwtsecurity.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service

public class JwtService {
private static final String SECRET="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    public String extractUsername(String jwt) {

        return extractClaim(jwt , Claims::getSubject);
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String jwt , Function<Claims , T> claimsResolver){
        final Claims  claims = extractAllClaims((jwt));
        return claimsResolver.apply(claims);

    }

    private Claims extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
        private Key getSignKey() {
           byte[] keyBytes= Decoders.BASE64.decode(SECRET);
           return Keys.hmacShaKeyFor(keyBytes);
        }
            public String generteToken(UserDetails userDetails){
        return generteToken(new HashMap<>(),userDetails);
}
       public String generteToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
         ){
             return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

          }

    public Boolean IsTokenValid(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
