package com.gestaotech.api.service;

import com.gestaotech.api.dto.Subject.SubjectMandatoryRequestDto;
import com.gestaotech.api.dto.Subject.SubjectResponseDto;
import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.infra.exceptions.StructureNotFoundException;
import com.gestaotech.api.infra.exceptions.SubjectNotFoundException;
import com.gestaotech.api.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StructureService {

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private SubjectService subjectService;


    public Structure findStructureById(Integer id) {
        return structureRepository.findById(id).orElseThrow(StructureNotFoundException::new);
    }

}
