package com.gestaotech.api.service;

import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import com.gestaotech.api.entity.SemesterSubject;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.repository.SemesterSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemesterSubjectService {

    @Autowired
    private SemesterSubjectRepository semesterSubjectRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    public SemesterSubject createSemesterSubject(SemesterSubjectCreateDto semesterSubjectCreateDto, SemesterUser semesterUser) {
        SemesterSubject semesterSubject = SemesterSubject.builder()
                .subject(subjectService.getSubject(semesterSubjectCreateDto.getSubjectId()))
                .semesterUser(semesterUser)
                .finished(semesterSubjectCreateDto.isFinished())
                .teacher(teacherService.findTeacherById(semesterSubjectCreateDto.getTeacherId()))
                .build();

        return semesterSubjectRepository.save(semesterSubject);

    }
}
