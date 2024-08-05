package com.gestaotech.api.service;

import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.infra.exceptions.StructureNotFoundException;
import com.gestaotech.api.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StructureService {

    @Autowired
    private StructureRepository structureRepository;

    public Structure findStructureById(Integer id) {
        return structureRepository.findById(id).orElseThrow(StructureNotFoundException::new);
    }
}
