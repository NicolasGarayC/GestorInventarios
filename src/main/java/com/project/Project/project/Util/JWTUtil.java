package com.project.Project.project.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {

    private String SECRET_KEY = "9vApxLk5G3PAsJrM067zWq4bR2zO2q5T3tC0A5a6S";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        try{
            return Jwts.builder().setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

        }catch (Exception e){
            throw new RuntimeException("El usuario se ha inhabilitado por intentos de sesion fallidos. Se ha enviado un token a su correo para habilitar su usuario");

        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = userDetails.getUsername();
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}