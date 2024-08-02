package com.gestaotech.api.repository;

import com.gestaotech.api.entity.SemesterSubjects;
import com.gestaotech.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterSubjectsRepository extends JpaRepository<SemesterSubjects,String> {
}
