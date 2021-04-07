package com.codeup.codeup_demo.repositories;

import com.codeup.codeup_demo.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
