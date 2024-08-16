package com.gestaotech.api.controller;

import com.gestaotech.api.dto.Auth.AuthRequestDto;
import com.gestaotech.api.dto.Auth.AuthResponseDto;
import com.gestaotech.api.dto.Auth.GoogleAuthRequestDto;
import com.gestaotech.api.dto.User.UserCreateDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.User;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.gestaotech.api.service.JwtService;
import com.gestaotech.api.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    NetHttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new GsonFactory();

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticateAndGetToken(@RequestBody @Valid AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            User user = userService.findUserByEmail(authRequestDto.getUsername());
            return ResponseEntity.ok().body(new AuthResponseDto(new UserResponseDto(user), jwtService.generateToken(authRequestDto.getUsername())));

        } else {
            throw new UserNotFoundException();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerAndGetToken(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = userService.createUser(userCreateDto);
        return ResponseEntity.ok().body(new AuthResponseDto(new UserResponseDto(user), jwtService.generateToken(user.getUsername())));
    }

    
}

