package com.gestaotech.api.dto.SemesterSubject;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SemesterSubjectCreateDto {
    @JsonProperty("subject_id")
    @NotBlank
    private String subjectId;
    @JsonProperty("teacher_id")
    @NotBlank
    private String teacherId;
}
