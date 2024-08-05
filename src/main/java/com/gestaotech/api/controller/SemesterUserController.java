package com.gestaotech.api.controller;

import com.gestaotech.api.dto.SemesterUser.SemesterUserCreateDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserResponseDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserUpdateDto;
import com.gestaotech.api.service.SemesterUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semester_user")
public class SemesterUserController {

    @Autowired
    private SemesterUserService semesterUserService;

    @PostMapping
    public ResponseEntity<SemesterUserResponseDto> createSemesterUser(@RequestBody @Valid SemesterUserCreateDto semesterUserCreateDto) {
        return ResponseEntity.ok().body(semesterUserService.createSemesterUser(semesterUserCreateDto));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<SemesterUserResponseDto>> getAllSemesterUserByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(semesterUserService.getAllSemesterUserByUserId(userId));
    }

    @GetMapping("{semesterUserId}")
    public ResponseEntity<SemesterUserResponseDto> getSemesterUserById(@PathVariable("semesterUserId") String semesterUserId) {
        return ResponseEntity.ok().body(semesterUserService.getSemesterUserById(semesterUserId));
    }

    @PutMapping("{semesterUserId}")
    public ResponseEntity<SemesterUserResponseDto> updateSemesterUser(@PathVariable("semesterUserId") String semesterUserId, @RequestBody SemesterUserUpdateDto semesterUserUpdateDto) {
        return ResponseEntity.ok().body(semesterUserService.updateSemesterUser(semesterUserId, semesterUserUpdateDto));
    }


}
