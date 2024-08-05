package com.gestaotech.api.dto.SemesterSubject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.Subject.SubjectResponseDto;
import com.gestaotech.api.entity.SemesterSubject;
import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.entity.Teacher;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
public class SemesterSubjectResponseDto {
    private String id;
    private SubjectResponseDto subject;
    private Teacher teacher;
    private boolean finished;

    public SemesterSubjectResponseDto(SemesterSubject semesterSubject){
        this.id=semesterSubject.getId();
        this.subject=new SubjectResponseDto(semesterSubject.getSubject());
        this.teacher=semesterSubject.getTeacher();
        this.finished=semesterSubject.isFinished();

    }


}
