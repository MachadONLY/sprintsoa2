package com.faculdade.service;

import com.faculdade.domain.User;
import com.faculdade.dto.AuthResponse;
import com.faculdade.dto.LoginRequest;
import com.faculdade.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Serviço de autenticação.
 * Responsável por autenticar usuários e gerar tokens JWT.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * Autentica um usuário e gera um token JWT.
     *
     * @param request dados de login
     * @return resposta com token JWT
     * @throws IllegalArgumentException se as credenciais forem inválidas
     */
    public AuthResponse authenticate(LoginRequest request) {
        log.info("Autenticando usuário com email: {}", request.getEmail());

        // Busca o usuário pelo email
        User user = userService.getUserByEmail(request.getEmail());

        // Valida a senha
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Falha na autenticação: senha inválida para email: {}", request.getEmail());
            throw new IllegalArgumentException("Email ou senha inválidos");
        }

        // Gera o token JWT
        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getId());

        log.info("Usuário autenticado com sucesso: {}", request.getEmail());

        // Retorna a resposta com o token
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getExpirationTime())
                .user(userService.getUserById(user.getId()))
                .build();
    }
}

