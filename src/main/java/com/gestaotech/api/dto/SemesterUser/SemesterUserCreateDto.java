package com.gestaotech.api.dto.SemesterUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SemesterUserCreateDto {
    @Min(1)
    @Max(10)
    private Integer semester;
    @NotNull
    private List<SemesterSubjectCreateDto> subjects;
    @JsonProperty("user_id")
    @NotBlank
    private String userId;
}
