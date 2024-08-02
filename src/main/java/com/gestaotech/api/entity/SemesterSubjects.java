package com.gestaotech.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table(name = "semester_subjects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SemesterSubjects {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private SemesterUser semesterUser;
    private boolean finished;
}
