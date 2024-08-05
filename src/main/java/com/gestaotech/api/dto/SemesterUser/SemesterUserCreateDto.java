package com.gestaotech.api.dto.SemesterUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import lombok.Data;

import java.util.List;

@Data
public class SemesterUserCreateDto {
    private Integer semester;
    private List<SemesterSubjectCreateDto> subjects;
    @JsonProperty("user_id")
    private String userId;
}
