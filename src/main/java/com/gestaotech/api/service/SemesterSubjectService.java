package com.gestaotech.api.service;

import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import com.gestaotech.api.dto.Subject.SubjectRequestDto;
import com.gestaotech.api.entity.SemesterSubject;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.entity.Teacher;
import com.gestaotech.api.infra.exceptions.SemesterSubjectNotFoundException;
import com.gestaotech.api.infra.exceptions.SemesterUserNotFoundException;
import com.gestaotech.api.repository.SemesterSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                .subject(subjectService.findSubject(semesterSubjectCreateDto.getSubjectId()))
                .semesterUser(semesterUser)
                .teacher(teacherService.findTeacherById(semesterSubjectCreateDto.getTeacherId()))
                .build();

        return semesterSubjectRepository.save(semesterSubject);
    }

    public SemesterSubject findSemesterSubjectById(String id){
        try{
        return semesterSubjectRepository.findById(id).orElseThrow();
        }
        catch (Exception e){
            throw new SemesterSubjectNotFoundException(id);
        }
    }

    public SemesterSubject findSemesterOrCreate(SubjectRequestDto subject){
        Optional<SemesterSubject> optionalSemesterUser = semesterSubjectRepository.findById(subject.getId());
        return optionalSemesterUser.orElseGet(() -> semesterSubjectRepository.save(new SemesterSubject()));

    }

}
