package com.gestaotech.api.service;

import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectResponseDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserCreateDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserResponseDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserUpdateDto;
import com.gestaotech.api.entity.SemesterSubject;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.User;
import com.gestaotech.api.infra.exceptions.SemesterUserNotFoundException;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.gestaotech.api.repository.SemesterUserRepository;
import com.gestaotech.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SemesterUserService {

    @Autowired
    private SemesterUserRepository semesterUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    public SemesterUserResponseDto createSemesterUser(SemesterUserCreateDto semesterUserCreateDto) {
        Optional<User> optionalUser = userRepository.findById(semesterUserCreateDto.getUserId());
        if (optionalUser.isEmpty()) throw new UserNotFoundException();
        SemesterUser semesterUser = SemesterUser.builder()
                .semester(semesterUserCreateDto.getSemester())
                .user(optionalUser.get())
                .build();
        semesterUser
                .setSubjects(semesterUserCreateDto
                        .getSubjects().stream()
                        .map(item -> subjectDtoToSubject(item, semesterUser)).toList());
        semesterUserRepository.save(semesterUser);
        return new SemesterUserResponseDto(semesterUser);
    }

    public List<SemesterUserResponseDto> getAllSemesterUserByUserId(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) throw new UserNotFoundException();
        List<SemesterUser> semesterUsers = semesterUserRepository.findAllByUser(optionalUser.get());
        return semesterUsers.stream().map(SemesterUserResponseDto::new).toList();
    }


    public SemesterUserResponseDto getSemesterUserById(String semesterUserId) {
        Optional<SemesterUser> optionalSemesterUser = semesterUserRepository.findById(semesterUserId);
        if (optionalSemesterUser.isEmpty()) throw new SemesterUserNotFoundException();
        return new SemesterUserResponseDto(optionalSemesterUser.get());

    }

    public SemesterUserResponseDto updateSemesterUser(String semesterUserId, SemesterUserUpdateDto semesterUserUpdateDto) {
        Optional<SemesterUser> optionalSemesterUser = semesterUserRepository.findById(semesterUserId);
        if (optionalSemesterUser.isEmpty()) throw new SemesterUserNotFoundException();
        SemesterUser semesterUser = optionalSemesterUser.get();
        semesterUser.setSubjects(semesterUserUpdateDto.getSubjects().stream().map(item -> subjectDtoToSubject(item, semesterUser)).toList());
        return new SemesterUserResponseDto(semesterUserRepository.save(semesterUser));
    }


    private SemesterSubject subjectDtoToSubject(SemesterSubjectCreateDto semesterSubjectCreateDto, SemesterUser semesterUser) {
        return SemesterSubject.builder()
                .id(semesterSubjectCreateDto.getSubjectId())
                .semesterUser(semesterUser)
                .subject(subjectService.getSubject(semesterSubjectCreateDto.getSubjectId()))
                .teacher(teacherService.findTeacherById(semesterSubjectCreateDto.getTeacherId()))
                .finished(semesterSubjectCreateDto.isFinished())
                .build();
    }

}
