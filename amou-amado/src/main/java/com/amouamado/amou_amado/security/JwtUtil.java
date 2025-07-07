package com.amouamado.amou_amado.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "chaveSecreta123456789012345678901234"; // Mínimo 32 caracteres
    private final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // Gera um token JWT com o e-mail e o ID do organizador
    public String gerarToken(String email, Long id) {
        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Valida se o token é válido e não expirou
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extrai o e-mail (subject) do token
    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Extrai o ID do organizador do token
    public Long getIdOrganizador(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);
    }
}
