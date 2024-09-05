package com.gestaotech.api.service;

import com.gestaotech.api.entity.Structure;
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

    @Autowired
    private StructureService structureService;

    public Subject findSubject(String id) {
        try {
            return subjectRepository.findById(id).orElseThrow();
        } catch (Exception exception) {
            throw new SubjectNotFoundException(id);
        }
    }

    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByIds(List<String> ids) {
        return subjectRepository.findAllById(ids);
    }

    public List<Subject> findOptionalSubjectsByStructureId(Integer structureId) {
        Structure structure = structureService.findStructureById(structureId);
        List<Subject> subjectList = findAllSubjects();
        return subjectList.stream().filter(item -> !structure.getMandatorySubjects().contains(item)).toList();
    }
}
