package com.gestaotech.api.dto.Teacher;

import com.gestaotech.api.entity.Teacher;
import lombok.Data;

@Data
public class TeacherResponseDto {
    private String id;
    private String name;
    private Double rating;


    public TeacherResponseDto(Teacher teacher){
        this.id=teacher.getId();
        this.name=teacher.getName();
        this.rating=teacher.getRating();
    }
}
