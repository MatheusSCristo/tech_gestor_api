package com.gestaotech.api.infra.exceptions;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(String id) {
        super("Componente curricular " + id + " não encontrado");
    }
}
