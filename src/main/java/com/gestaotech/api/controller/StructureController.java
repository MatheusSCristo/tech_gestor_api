package com.gestaotech.api.controller;

import com.gestaotech.api.dto.Structure.StructureResponseDto;
import com.gestaotech.api.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/structure")

public class StructureController {

    @Autowired
    private StructureService structureService;


    @GetMapping("{id}")
    public ResponseEntity<StructureResponseDto> findStructureById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new StructureResponseDto(structureService.findStructureById(id)));
    }

}
