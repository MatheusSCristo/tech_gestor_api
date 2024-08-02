package com.gestaotech.api.repository;

import com.gestaotech.api.entity.SemesterUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterUserRepository extends JpaRepository<SemesterUser,String> {
}
