package com.gestaotech.api.service;

import com.gestaotech.api.entity.Teacher;
import com.gestaotech.api.infra.exceptions.TeacherNotFoundException;
import com.gestaotech.api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findTeacherById(String id){
        return teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);
    }
}
