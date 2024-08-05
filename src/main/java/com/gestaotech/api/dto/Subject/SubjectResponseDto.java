package com.gestaotech.api.dto.Subject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.entity.Subject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectResponseDto {
    private String id;
    private String name;
    private Integer ch;
    private String description;
    @JsonProperty("pre_requisites")
    private List<SubjectResponseDto> preRequisites = new ArrayList<>();
    @JsonProperty("co_requisites")
    private List<SubjectResponseDto> coRequisites = new ArrayList<>();

    public SubjectResponseDto(Subject subject){
        this.id=subject.getId();
        this.name=subject.getName();
        this.ch=subject.getCh();
        this.description=subject.getDescription();
        this.preRequisites=subject.getPreRequisites().stream().map(SubjectResponseDto::new).toList();
        this.coRequisites=subject.getCoRequisites().stream().map(SubjectResponseDto::new).toList();


    }

}
