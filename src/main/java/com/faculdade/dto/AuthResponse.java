package com.faculdade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * DTO para resposta de autenticação contendo o token JWT.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Resposta de autenticação com token JWT")
public class AuthResponse {

    @Schema(description = "Token JWT para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Tipo de token", example = "Bearer")
    private String tokenType;

    @Schema(description = "Tempo de expiração em milissegundos", example = "86400000")
    private Long expiresIn;

    @Schema(description = "Dados do usuário autenticado")
    private UserDTO user;
}

