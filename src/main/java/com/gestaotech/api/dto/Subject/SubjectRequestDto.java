package com.gestaotech.api.dto.Subject;

import lombok.Data;

@Data
public class SubjectRequestDto {

    private String id;
    private String subjectId;
    private String teacherId;
    private boolean finished;

}
