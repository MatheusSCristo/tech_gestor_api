package com.gestaotech.api.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private SemesterUser semesterUser;
    private boolean finished=false;

    public SemesterSubject(Subject subject,Teacher teacher,SemesterUser semesterUser){
        this.subject=subject;
        this.teacher=teacher;
        this.semesterUser=semesterUser;
    }
}
