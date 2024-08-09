package com.gestaotech.api.controller;

import com.gestaotech.api.dto.User.UserCreateDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

}
