package com.gestaotech.api.service;

import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.infra.exceptions.SubjectNotFoundException;
import com.gestaotech.api.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    public Subject getSubject(String id){
        return subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }
}
