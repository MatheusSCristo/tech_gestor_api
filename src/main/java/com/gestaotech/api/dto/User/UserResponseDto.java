package com.gestaotech.api.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.SemesterUser.SemesterUserResponseDto;
import com.gestaotech.api.dto.Structure.StructureResponseDto;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class UserResponseDto {
    private String id;
    private String name;
    private StructureResponseDto structure;
    private String start;
    private String email;
    private String password;
    @JsonProperty("image_url")
    private String imageUrl;
    private List<SemesterUserResponseDto> semesters = new ArrayList<>();


    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.structure = new StructureResponseDto(user.getStructure());
        this.start = user.getStart();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
        this.semesters = user.getSemesters().stream().map(SemesterUserResponseDto::new).toList();
    }

}
