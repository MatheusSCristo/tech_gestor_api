package com.gestaotech.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @ManyToOne
    private Structure structure;
    private String start;
    private String email;
    private String password;
    private String image_url;
    @OneToMany(mappedBy = "user")
    private List<SemesterUser> semesters=new ArrayList<>();
}
