package com.codeup.codeup_demo.repositories;

import java.util.List;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdDesc();

    Post getOne(Long id);
    List<Post> findAllByOwner(User user);

}
