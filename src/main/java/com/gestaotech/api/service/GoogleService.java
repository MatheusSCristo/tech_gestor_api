package com.gestaotech.api.service;

import com.gestaotech.api.dto.Auth.AuthResponseDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.User;
import com.gestaotech.api.infra.exceptions.GoogleEmailException;
import com.gestaotech.api.infra.exceptions.GoogleIdTokenNotVerifiedException;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
public class GoogleService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    NetHttpTransport transport = new NetHttpTransport();

    JsonFactory jsonFactory = new GsonFactory();
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    public Boolean verifyIdToken(String idToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singleton(clientId))
                .build();
        try {
            GoogleIdToken token = verifier.verify(idToken);
            if (token != null) {
                GoogleIdToken.Payload payload = token.getPayload();
                String email = payload.getEmail();
                try {
                    userService.findUserByEmail(email);
                    return true;
                } catch (UserNotFoundException exception) {
                    throw new GoogleEmailException();
                }
            }
        } catch (GeneralSecurityException |
                 IOException exception) {
            throw new RuntimeException();
        }
        return null;
    }

    public UserResponseDto getUser(String email) {
        return new UserResponseDto(userService.findUserByEmail(email));

    }
}
