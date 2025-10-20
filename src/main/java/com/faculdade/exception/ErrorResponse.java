package com.faculdade.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Classe padrão para respostas de erro da API.
 * Fornece uma estrutura consistente para todas as respostas de erro.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Map<String, String> errors;
    private String path;
}

