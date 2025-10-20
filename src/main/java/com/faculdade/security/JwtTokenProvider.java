package com.faculdade.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Componente responsável pela geração e validação de tokens JWT.
 * Implementa a lógica de autenticação stateless usando JWT.
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Gera um token JWT para um usuário.
     *
     * @param email o email do usuário
     * @param userId o ID do usuário
     * @return o token JWT gerado
     */
    public String generateToken(String email, Long userId) {
        log.info("Gerando token JWT para usuário: {}", email);

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrai o email (subject) do token JWT.
     *
     * @param token o token JWT
     * @return o email contido no token
     */
    public String getEmailFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Extrai o ID do usuário do token JWT.
     *
     * @param token o token JWT
     * @return o ID do usuário contido no token
     */
    public Long getUserIdFromToken(String token) {
        return getAllClaimsFromToken(token).get("userId", Long.class);
    }

    /**
     * Valida se o token JWT é válido.
     *
     * @param token o token JWT
     * @return true se o token é válido, false caso contrário
     */
    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("Falha na validação do token JWT: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Extrai todas as claims do token JWT.
     *
     * @param token o token JWT
     * @return as claims contidas no token
     */
    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtém o tempo de expiração do token em milissegundos.
     *
     * @return o tempo de expiração configurado
     */
    public long getExpirationTime() {
        return jwtExpiration;
    }
}

