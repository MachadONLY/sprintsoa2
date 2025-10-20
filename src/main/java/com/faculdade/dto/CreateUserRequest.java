package com.faculdade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO para requisição de criação de usuário.
 * Valida os dados de entrada antes de processar.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para criar um novo usuário")
public class CreateUserRequest {

    @Schema(description = "Nome do usuário", example = "João Silva")
    @NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @Schema(description = "Email do usuário", example = "joao@example.com")
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Schema(description = "Senha do usuário", example = "senha123")
    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
    private String password;
}

