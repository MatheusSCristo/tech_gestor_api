package com.gestaotech.api.repository;

import com.gestaotech.api.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,String> {
}
