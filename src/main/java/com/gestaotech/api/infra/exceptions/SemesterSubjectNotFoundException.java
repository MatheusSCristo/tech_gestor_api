package com.gestaotech.api.infra.exceptions;

public class SemesterSubjectNotFoundException extends RuntimeException{
    public SemesterSubjectNotFoundException(String id){
        super("Semestre componente com id="+id + " não encontrado");
    }
}
