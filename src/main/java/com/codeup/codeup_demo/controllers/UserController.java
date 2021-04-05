package com.codeup.codeup_demo.controllers;


import com.codeup.codeup_demo.models.Image;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.Profile;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repositories.PostRepository;
import com.codeup.codeup_demo.repositories.ProfileRepository;
import com.codeup.codeup_demo.repositories.UserRepository;
import com.codeup.codeup_demo.services.UserService;
import com.codeup.codeup_demo.util.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    @Autowired
    UserRepository userDao;
    @Autowired
    ProfileRepository profileDao;
    @Autowired
    PostRepository postDao;
    @Autowired
    UserService usersSvc;

    @GetMapping("/post/create")
    public String createPost(Model model
    ) {

        

        model.addAttribute("post", new Post());
        return "createPost";
    }

    @GetMapping("/post/edit/{id}")
    public String editPost(
        Model model,
        @PathVariable Long id) {

        User user = usersSvc.getCurrentUser();
        Post post = postDao.getOne(id);

        model.addAttribute("post", post);
        return "editPost";
    }

    
    @GetMapping("/post/view/{id}")
    @PreAuthorize("permitAll()")
    public String viewPost(
        Model model,
        @PathVariable Long id
    ){

        Post post = postDao.getOne(id);

        if(post == null){
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "post";
    }
    
}
