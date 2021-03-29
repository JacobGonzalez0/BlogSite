package com.codeup.codeup_demo.repos;

import java.util.List;

import com.codeup.codeup_demo.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdDesc();

    Post getOne(Long id);

}