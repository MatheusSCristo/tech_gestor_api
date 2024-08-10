package com.gestaotech.api.infra.exceptions;

public class SemesterUserNotFoundException extends RuntimeException {
    public SemesterUserNotFoundException() {
        super("Semestre do usuário não encontrado");
    }
}
