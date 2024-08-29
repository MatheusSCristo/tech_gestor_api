package com.gestaotech.api.service;

import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.infra.exceptions.SubjectNotFoundException;
import com.gestaotech.api.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    public Subject findSubject(String id) {
        return subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }

    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByIds(List<String> ids){
        return subjectRepository.findAllById(ids);
    }
}
