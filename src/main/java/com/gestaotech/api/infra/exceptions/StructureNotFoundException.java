package com.gestaotech.api.infra.exceptions;

public class StructureNotFoundException extends RuntimeException {
    public StructureNotFoundException() {
        super("Estrutura curricular não encontrada");
    }
}
