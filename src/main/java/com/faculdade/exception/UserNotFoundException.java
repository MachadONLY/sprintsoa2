package com.faculdade.exception;

/**
 * Exceção lançada quando um usuário não é encontrado no banco de dados.
 * Segue o padrão de exceções customizadas para melhor tratamento de erros.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

