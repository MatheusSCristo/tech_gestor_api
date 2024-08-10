package com.gestaotech.api.infra.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
