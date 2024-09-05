package com.gestaotech.api.controller;

import com.gestaotech.api.dto.SemesterUser.SemesterUserResponseDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserUpdateDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.User;
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

    @PostMapping("{id}")
    public ResponseEntity<List<SemesterUserResponseDto>> saveSemesters(@PathVariable String id,@RequestBody @Valid SemesterUserUpdateDto semesters) {
        return ResponseEntity.ok().body(semesterUserService.saveSemesters(semesters,id));
    }

    @GetMapping("/getAllByUserId/{userId}")
    public ResponseEntity<List<SemesterUserResponseDto>> getAllSemesterUserByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(semesterUserService.getAllSemesterUserByUserId(userId));
    }

    @GetMapping("/getSemesterUserById/{semesterUserId}")
    public ResponseEntity<SemesterUserResponseDto> getSemesterUserById(@PathVariable("semesterUserId") String semesterUserId) {
        return ResponseEntity.ok().body(semesterUserService.getSemesterUserById(semesterUserId));
    }

    @PutMapping("{userId}")
    public ResponseEntity<List<SemesterUserResponseDto>> resetSemesterToDefault(@PathVariable("userId") String userId) {
        return ResponseEntity.ok().body(semesterUserService.resetSemesterToDefault(userId).stream().map(SemesterUserResponseDto::new).toList());
    }


}
