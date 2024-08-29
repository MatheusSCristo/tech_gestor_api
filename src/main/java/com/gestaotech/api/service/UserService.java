package com.gestaotech.api.service;

import com.gestaotech.api.dto.SemesterUser.Default.Computacao;
import com.gestaotech.api.dto.SemesterUser.Default.Software;
import com.gestaotech.api.dto.SemesterUser.Default.TiMatutino;
import com.gestaotech.api.dto.SemesterUser.Default.TiNoturno;
import com.gestaotech.api.dto.User.GoogleUserCreateDto;
import com.gestaotech.api.dto.User.UserCreateDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.*;
import com.gestaotech.api.infra.exceptions.EmailAlreadyRegisteredException;
import com.gestaotech.api.infra.exceptions.StartTimeNotValidException;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.gestaotech.api.repository.SemesterSubjectRepository;
import com.gestaotech.api.repository.SemesterUserRepository;
import com.gestaotech.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.gestaotech.api.enums.StructureEnum.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StructureService structureService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SemesterUserRepository semesterUserRepository;

    @Autowired
    private SemesterSubjectRepository semesterSubjectRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User createUser(UserCreateDto userCreateDto) {
        Optional<User> optionalUserWithSameEmail = userRepository.findByEmail(userCreateDto.getEmail());
        if (!validateStartTime(userCreateDto.getStart())) {
            throw new StartTimeNotValidException();
        }
        if (optionalUserWithSameEmail.isPresent()) {
            throw new EmailAlreadyRegisteredException();
        }

        Structure structure = structureService.findStructureById(userCreateDto.getStructureId().getValue());
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .start(userCreateDto.getStart())
                .name(userCreateDto.getName())
                .password(encoder.encode(userCreateDto.getPassword()))
                .imageUrl(userCreateDto.getImageUrl())
                .structure(structure)
                .build();
        User userWithDefaultSemesters = selectDefaultUserSemesters(user);
        return userRepository.save(userWithDefaultSemesters);
    }

    public User createGoogleUser(GoogleUserCreateDto googleUserCreateDto) {
        Optional<User> optionalUserWithSameEmail = userRepository.findByEmail(googleUserCreateDto.getEmail());
        if (!validateStartTime(googleUserCreateDto.getStart())) {
            throw new StartTimeNotValidException();
        }
        if (optionalUserWithSameEmail.isPresent()) {
            throw new EmailAlreadyRegisteredException();
        }

        Structure structure = structureService.findStructureById(googleUserCreateDto.getStructureId().getValue());
        User user = User.builder()
                .email(googleUserCreateDto.getEmail())
                .start(googleUserCreateDto.getStart())
                .name(googleUserCreateDto.getName())
                .imageUrl(googleUserCreateDto.getImageUrl())
                .structure(structure)
                .build();
       userRepository.save(user);
        User userWithDefaultSemesters = selectDefaultUserSemesters(user);
        return userRepository.save(userWithDefaultSemesters);
    }

    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }


    private boolean validateStartTime(String time) {
        String year = time.split("\\.")[0];
        String semester = time.split("\\.")[1];
        return LocalDateTime.now().getYear() - Integer.parseInt(year) <= 10 && (Objects.equals(semester, "2") || Objects.equals(semester, "1"));
    }

    private User selectDefaultUserSemesters(User user) {
        switch (user.getStructure().getId()) {
            case 0 -> user.setSemesters(getDefaultUserSemesters(user, new TiMatutino().getList()));
            case 1 -> user.setSemesters(getDefaultUserSemesters(user, new TiNoturno().getList()));
            case 2 -> user.setSemesters(getDefaultUserSemesters(user, new Software().getList()));
            case 3 -> user.setSemesters(getDefaultUserSemesters(user, new Computacao().getList()));
        }
        return user;
    }

    private List<SemesterUser> getDefaultUserSemesters(User user, List<List<String>> semesters) {
        return IntStream.range(0, semesters.size())
                .mapToObj(i -> {
                    SemesterUser semesterUser=new SemesterUser(i+1,user);
                    semesterUser.setSubjects(getListSemesterSubject(semesters.get(i),semesterUser));
                    return semesterUser;
                })
                .toList();
    }

    private List<SemesterSubject> getListSemesterSubject(List<String> list,SemesterUser semesterUser) {
        Teacher teacher = teacherService.findTeacherById("0");
        List<Subject> subjects = subjectService.getSubjectsByIds(list);
        return subjects.stream().map(item -> new SemesterSubject(item, teacher,semesterUser)).toList();
    }

}
