package com.gestaotech.api.dto.SemesterUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import com.gestaotech.api.dto.SemesterUser.Default.SemesterUserRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class SemesterUserUpdateDto {
    @JsonProperty("semesters")
    private List<SemesterUserRequestDto> semesters;
}
