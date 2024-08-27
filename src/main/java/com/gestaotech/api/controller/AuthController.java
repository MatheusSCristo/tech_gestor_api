package com.gestaotech.api.controller;

import com.gestaotech.api.dto.Auth.AuthRequestDto;
import com.gestaotech.api.dto.Auth.AuthResponseDto;
import com.gestaotech.api.dto.User.GoogleUserCreateDto;
import com.gestaotech.api.dto.User.UserCreateDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.User;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.gestaotech.api.service.GoogleService;
import com.gestaotech.api.service.JwtService;
import com.gestaotech.api.service.UserInfoService;
import com.gestaotech.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GoogleService googleService;

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

    @PostMapping("/google/register")
    public ResponseEntity<AuthResponseDto> googleRegister(@RequestBody @Valid GoogleUserCreateDto googleUserCreateDto) {
        User user = userService.createGoogleUser(googleUserCreateDto);
        return ResponseEntity.ok().body(new AuthResponseDto(new UserResponseDto(user), jwtService.generateToken(user.getUsername())));
    }


    @GetMapping("/google/{idToken}")
    public ResponseEntity<Void> verifyGoogleIdToken(@PathVariable String idToken) throws GeneralSecurityException, IOException {
        if (googleService.verifyIdToken(idToken)) {
            return ResponseEntity.ok().build();
        }
        return null;
    }

    @GetMapping("/validate/{accessToken}")
    public ResponseEntity<Void> validateToken(@PathVariable String accessToken) {
        UserDetails userDetails = userInfoService.loadUserByUsername(jwtService.extractUsername(accessToken));
        if (jwtService.validateToken(accessToken, userDetails)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("google/validate/{email}")
    public ResponseEntity<AuthResponseDto> getUserLoggedByGoogle(@PathVariable String email){
        UserResponseDto userResponseDto=googleService.getUser(email);
        String token=jwtService.generateToken(userResponseDto.getEmail());
        return ResponseEntity.ok().body(new AuthResponseDto(userResponseDto,token));
    }

}

