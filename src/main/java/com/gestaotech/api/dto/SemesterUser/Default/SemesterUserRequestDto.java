package com.gestaotech.api.dto.SemesterUser.Default;

import com.gestaotech.api.dto.Subject.SubjectRequestDto;
import com.gestaotech.api.entity.Subject;
import lombok.Data;

import java.util.List;

@Data
public class SemesterUserRequestDto {
    private String id;
    private Integer semester;
    private List<SubjectRequestDto> subjects;

}
