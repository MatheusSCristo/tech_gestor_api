package com.gestaotech.api.infra.config;

import com.gestaotech.api.infra.config.filter.RestErrorMessage;
import com.gestaotech.api.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    private ResponseEntity<RestErrorMessage> emailAlreadyRegisteredHandler(EmailAlreadyRegisteredException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(StructureNotFoundException.class)
    private ResponseEntity<RestErrorMessage> structureNotFoundHandler(StructureNotFoundException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    private ResponseEntity<RestErrorMessage> teacherNotFoundHandler(TeacherNotFoundException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(SemesterUserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> semesterUserNotFoundHandler(SemesterUserNotFoundException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    private ResponseEntity<RestErrorMessage> subjectNotFoundHandler(SubjectNotFoundException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestErrorMessage> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getFieldError().getDefaultMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<RestErrorMessage> badCredentialsHandler(BadCredentialsException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), "Erro na validação das credenciais");
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<RestErrorMessage> httpMessageNotReadableHandler(HttpMessageNotReadableException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), "Erro na hora de deserializar objeto");
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(StartTimeNotValidException.class)
    private ResponseEntity<RestErrorMessage> startTimeNotValidHandler(StartTimeNotValidException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


}
