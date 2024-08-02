package com.gestaotech.api.entity;


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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer ch;
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

    @ManyToOne
    @JoinColumn(name = "structure_mandatory_id")
    private Structure structureMandatory;

    @ManyToOne
    @JoinColumn(name = "structure_optional_id")
    private Structure structureOptional;

    @OneToMany
    private List<SemesterSubjects> semesterSubjects;
}
