package com.gestaotech.api.dto.SemesterSubject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SemesterSubjectCreateDto {
    @JsonProperty("subject_id")
    private String subjectId;
    @JsonProperty("teacher_id")
    private String teacherId;
}
