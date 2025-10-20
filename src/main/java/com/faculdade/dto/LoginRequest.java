package com.faculdade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO para requisição de autenticação (login).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para autenticação do usuário")
public class LoginRequest {

    @Schema(description = "Email do usuário", example = "joao@example.com")
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123")
    @NotBlank(message = "A senha não pode estar vazia")
    private String password;
}

