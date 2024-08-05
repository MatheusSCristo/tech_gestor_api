package com.gestaotech.api.dto.SemesterUser;

import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectResponseDto;
import com.gestaotech.api.entity.SemesterSubject;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SemesterUserResponseDto {
    private String id;
    private Integer semester;
    private List<SemesterSubjectResponseDto> subjects = new ArrayList<>();

    public SemesterUserResponseDto(SemesterUser semesterUser) {
        this.id = semesterUser.getId();
        this.semester = semesterUser.getSemester();
        this.subjects = semesterUser.getSubjects().stream().map(SemesterSubjectResponseDto::new).toList();
    }
}
