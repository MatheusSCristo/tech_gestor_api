package com.gestaotech.api.entity;


import com.gestaotech.api.dto.Subject.SubjectResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject {
    @Id
    private String id;
    private String name;
    private Integer ch;
    @Column(columnDefinition = "TEXT")
    private String description;
    @JoinTable(
            name = "subject_pre_requisites",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "pre_requisite_id")
    )
    @ManyToMany
    private List<Subject> preRequisites = new ArrayList<>();
    @ManyToMany

    @JoinTable(
            name = "subject_co_requisites",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "co_requisite_id")
    )
    private List<Subject> coRequisites = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "subject_structure_mandatory",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "structure_mandatory_id")
    )
    private List<Structure> structureMandatory = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "subject_structure_optional",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "structure_optional_id")
    )
    private List<Structure> structureOptional = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<SemesterSubject> semesterSubjects;


}
