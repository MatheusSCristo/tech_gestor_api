package com.gestaotech.api.dto.Teacher;

import com.gestaotech.api.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponseDto {
    private String id;
    private String name;
    private List<Integer> rating;


    public TeacherResponseDto(Teacher teacher){
        this.id=teacher.getId();
        this.name=teacher.getName();
        this.rating=teacher.getRating();
    }
}
