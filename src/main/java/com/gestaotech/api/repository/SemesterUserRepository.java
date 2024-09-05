package com.gestaotech.api.repository;

import com.gestaotech.api.entity.SemesterUser;
import com.gestaotech.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterUserRepository extends JpaRepository<SemesterUser,String> {

    public List<SemesterUser> findAllByUser(User user);

    public void deleteAllByUser(User user);
}
