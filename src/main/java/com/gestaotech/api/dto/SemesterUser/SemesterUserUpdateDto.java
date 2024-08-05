package com.gestaotech.api.dto.SemesterUser;

import com.gestaotech.api.dto.SemesterSubject.SemesterSubjectCreateDto;
import lombok.Data;

import java.util.List;

@Data
public class SemesterUserUpdateDto {
    private List<SemesterSubjectCreateDto> subjects;
}
