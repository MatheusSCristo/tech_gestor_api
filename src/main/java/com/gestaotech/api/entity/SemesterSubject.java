package com.gestaotech.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "semester_subjects")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SemesterSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private SemesterUser semesterUser;
    private boolean finished=false;
}
