package com.gestaotech.api.infra.exceptions;

import org.springframework.security.core.parameters.P;

public class StartTimeNotValidException extends RuntimeException{

    public StartTimeNotValidException(){
        super("Semestre de início não é valido");
    }
}
