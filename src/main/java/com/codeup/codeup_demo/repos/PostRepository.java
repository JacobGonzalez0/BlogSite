package com.codeup.codeup_demo.repos;

import java.util.List;

import com.codeup.codeup_demo.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    
    List<Post> findAllByOrderByIdDesc();



}