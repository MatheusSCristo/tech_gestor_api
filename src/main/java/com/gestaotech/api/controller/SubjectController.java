package com.gestaotech.api.controller;

import com.gestaotech.api.dto.Subject.SubjectResponseDto;
import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> findAllSubjects() {
        List<Subject> list = subjectService.findAllSubjects();
        return ResponseEntity.ok().body(list.stream().map(SubjectResponseDto::new).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectResponseDto> findSubjectById(@PathVariable String id) {
        return ResponseEntity.ok().body(new SubjectResponseDto(subjectService.findSubject(id)));
    }
}
