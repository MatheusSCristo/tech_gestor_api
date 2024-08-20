package com.gestaotech.api.infra.exceptions;

public class GoogleIdTokenNotVerifiedException extends RuntimeException{
    public GoogleIdTokenNotVerifiedException(){
        super("Google Id Token signature not verified");
    }
}
