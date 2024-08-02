package com.gestaotech.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "structure")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(name = "ch_subtotal")
    private Integer chSubtotal;
    @Column(name = "optional_ch_min")
    private Integer optionalChMin;
    @OneToMany(mappedBy = "structureMandatory")
    private List<Subject> mandatorySubjects = new ArrayList<>();
    @OneToMany(mappedBy = "structureOptional")
    private List<Subject> optionalSubjects = new ArrayList<>();
    @OneToMany(mappedBy = "structure")
    private List<User> users=new ArrayList<>();

}
