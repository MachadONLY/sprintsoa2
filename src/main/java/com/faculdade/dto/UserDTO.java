package com.faculdade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * DTO para transferência de dados de usuário.
 * Utilizado para respostas da API, não expõe dados sensíveis.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados de um usuário")
public class UserDTO {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome do usuário", example = "João Silva")
    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

    @Schema(description = "Email do usuário", example = "joao@example.com")
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Schema(description = "Status ativo do usuário", example = "true")
    private Boolean active;

    @Schema(description = "Data de criação (timestamp)", example = "1697000000000")
    private Long createdAt;
}

