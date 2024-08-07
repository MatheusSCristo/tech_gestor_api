package com.gestaotech.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semester_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SemesterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer semester;
    @OneToMany(mappedBy ="semesterUser")
    private List<SemesterSubject> subjects=new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
