package com.gestaotech.api.dto.Structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestaotech.api.dto.Subject.SubjectResponseDto;
import com.gestaotech.api.entity.Structure;
import com.gestaotech.api.entity.Subject;
import com.gestaotech.api.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StructureResponseDto {
    private Integer id;
    private String name;
    @JsonProperty("ch_subtotal")
    private Integer chSubtotal;
    @JsonProperty("optional_ch_min")
    private Integer optionalChMin;
    @JsonProperty("mandatory_subjects")
    private List<SubjectResponseDto> mandatorySubjects = new ArrayList<>();
    @JsonProperty("optional_subjects")
    private List<SubjectResponseDto> optionalSubjects = new ArrayList<>();

    public StructureResponseDto(Structure structure){
        this.id=structure.getId();
        this.name=structure.getName();
        this.chSubtotal=structure.getChSubtotal();
        this.optionalChMin=structure.getOptionalChMin();
        this.mandatorySubjects=structure.getMandatorySubjects().stream().map(SubjectResponseDto::new).toList();
        this.optionalSubjects=structure.getOptionalSubjects().stream().map(SubjectResponseDto::new).toList();
    }
}
