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
    @OneToMany(mappedBy ="semesterUser",cascade = CascadeType.ALL)
    private List<SemesterSubject> subjects=new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public SemesterUser(Integer semester,User user){
        this.semester=semester;
        this.user=user;
    }
    public SemesterUser(User user){
        this.user=user;
    }

}
