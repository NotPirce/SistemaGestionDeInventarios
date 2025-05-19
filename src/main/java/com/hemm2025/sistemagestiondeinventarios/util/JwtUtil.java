package com.hemm2025.sistemagestiondeinventarios.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;





/**
 *
 * @author notpi
 */
public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000;
    
    //Metodo que se encarga de generar el token
    public static String  generateToken(String username, String rol){
        
        // Esto lo que hace es generar un arreglo o una lista de claims
        Map<String, Object> claims = new HashMap<>();
        
        claims.put("username", username);
        claims.put("rol", rol);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key).compact();
    }
    
    public static boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;            
        }
    }
    
    public static Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
    
    public static String obtenerCorreo(String token){
        Claims claims = getClaims(token);
        String username = claims.get("username", String.class);
        return username;
    }
    
    public static String obtenerRol(String token){
        Claims claims = getClaims(token);
        return claims.get("rol", String.class);
    }
    
    
    
}
