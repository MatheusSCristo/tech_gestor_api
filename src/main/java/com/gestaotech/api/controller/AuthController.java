package com.gestaotech.api.controller;

import com.gestaotech.api.dto.Auth.AuthRequestDto;
import com.gestaotech.api.dto.User.UserCreateDto;
import com.gestaotech.api.entity.User;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.gestaotech.api.service.JwtService;
import com.gestaotech.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody @Valid AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequestDto.getUsername());
        } else {
            throw new UserNotFoundException();
        }
    }

    @PostMapping("/register")
    public String registerAndGetToken(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = userService.createUser(userCreateDto);
        return jwtService.generateToken(user.getUsername());

    }


}

