package com.codeup.codeup_demo.repositories;

import java.util.List;

import com.codeup.codeup_demo.models.AuthGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {
    List<AuthGroup> findByUsername(String username);
}
