package com.gestaotech.api.controller;

import com.gestaotech.api.dto.Teacher.TeacherResponseDto;
import com.gestaotech.api.entity.Teacher;
import com.gestaotech.api.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> findAllTeachers(){
        return ResponseEntity.ok().body(teacherService.findAllTeachers().stream().map(TeacherResponseDto::new).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherResponseDto> findTeacherById(@PathVariable String id){
        return ResponseEntity.ok().body(new TeacherResponseDto(teacherService.findTeacherById(id)));
    }
}
