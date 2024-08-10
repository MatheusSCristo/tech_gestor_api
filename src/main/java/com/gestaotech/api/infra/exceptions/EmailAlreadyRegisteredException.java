package com.gestaotech.api.infra.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {

    public EmailAlreadyRegisteredException() {
        super("Email em uso");
    }
}
