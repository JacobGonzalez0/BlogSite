package com.codeup.codeup_demo.repositories;

import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.models.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile findByUser(User user);
}
