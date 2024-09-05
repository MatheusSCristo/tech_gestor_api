package com.gestaotech.api.service;

import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectResponseDto;
import com.gestaotech.api.dto.SemesterUser.Default.SemesterUserRequestDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserResponseDto;
import com.gestaotech.api.dto.SemesterUser.SemesterUserUpdateDto;
import com.gestaotech.api.dto.Subject.SubjectRequestDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.SemesterSubject;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.Subject;
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
    private SemesterSubjectService semesterSubjectService;

    @Autowired
    private TeacherService teacherService;


    @Autowired
    private UserService userService;

    @Transactional
    public List<SemesterUserResponseDto> saveSemesters(SemesterUserUpdateDto semesterUserUpdateDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<SemesterUserRequestDto> semesterUsers=semesterUserUpdateDto.getSemesters();
        deleteAllUserSemesters(user);
        List<SemesterUser> semesterUserList=semesterUsers.stream().map(item->{
            SemesterUser semesterUser= new SemesterUser();
            semesterUser.setSemester(item.getSemester());
            semesterUser.setUser(user);
            semesterUser.setSubjects(item.getSubjects().stream().map(subject->getSubjects(subject,semesterUser)).toList());
            return semesterUser;
        }).toList();
        user.getSemesters().clear();
        user.getSemesters().addAll(semesterUserList);
        userRepository.save(user);

        return getAllSemesterUserByUserId(userId);
    }


    private SemesterSubject getSubjects(SubjectRequestDto subjectRequestDto, SemesterUser semesterUser) {
        SemesterSubject semesterSubject = semesterSubjectService.findSemesterOrCreate(subjectRequestDto);
        semesterSubject.setSubject(subjectService.findSubject(subjectRequestDto.getSubjectId()));
        semesterSubject.setTeacher(teacherService.findTeacherById(subjectRequestDto.getTeacherId()));
        semesterSubject.setSemesterUser(semesterUser);
        semesterSubject.setFinished(subjectRequestDto.isFinished());
        return semesterSubject;
    }

    public List<SemesterUserResponseDto>    getAllSemesterUserByUserId(String userId) {
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


    private SemesterSubject subjectDtoToSubject(SemesterSubjectCreateDto semesterSubjectCreateDto, SemesterUser semesterUser) {
        return SemesterSubject.builder()
                .id(semesterSubjectCreateDto.getSubjectId())
                .semesterUser(semesterUser)
                .subject(subjectService.findSubject(semesterSubjectCreateDto.getSubjectId()))
                .teacher(teacherService.findTeacherById(semesterSubjectCreateDto.getTeacherId()))
                .build();
    }

    public List<SemesterUser> resetSemesterToDefault(String userId) {
        User user=userService.findUserById(userId);
        deleteAllUserSemesters(user);
        user.getSemesters().clear();
        user.getSemesters().addAll(userService.selectDefaultUserSemesters(userService.findUserById(userId)));
        return userRepository.save(user).getSemesters();


    }

    public void deleteAllUserSemesters(User user){
        semesterUserRepository.deleteAllByUser(user);
    }

}
