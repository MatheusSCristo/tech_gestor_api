package com.gestaotech.api.service;

import com.gestaotech.api.dto.User.UserCreateDto;
import com.gestaotech.api.dto.User.UserResponseDto;
import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.entity.User;
import com.gestaotech.api.infra.exceptions.UserNotFoundException;
import com.gestaotech.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StructureService structureService;

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        Structure structure = structureService.findStructureById(userCreateDto.getStructureId());
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .start(userCreateDto.getStart())
                .name(userCreateDto.getName())
                .password(userCreateDto.getPassword())
                .imageUrl(userCreateDto.getImageUrl())
                .structure(structure)
                .build();
        return new UserResponseDto(userRepository.save(user));
    }

    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }
}
