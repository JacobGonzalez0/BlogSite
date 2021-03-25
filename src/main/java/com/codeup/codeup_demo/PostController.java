package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    
    @GetMapping("/post")
    public String seePost(){
        return "post";
    }

    @GetMapping("/post/{id}")
    public String byIdPosts(@PathVariable int id){
        return "heres post " + id;
    }

    @GetMapping("/post/create")
    public String viewPostFrom(){
        return "create a post here ";
    }

    @PostMapping(path = "/post/create")
    public String createPost() {
        return "created post here";
    }
}
