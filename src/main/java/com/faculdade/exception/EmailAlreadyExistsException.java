package com.faculdade.exception;

/**
 * Exceção lançada quando tenta-se criar um usuário com um email já existente.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

