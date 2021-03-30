package com.codeup.codeup_demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.Tag;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repos.PostRepository;
import com.codeup.codeup_demo.repos.UserRepository;
import com.codeup.codeup_demo.services.UserService;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final UserService usersSvc;


    public HomeController(UserRepository userDao, PostRepository postDao, UserService usersSvc) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.usersSvc = usersSvc;
    }

    @GetMapping(value="/login")
    public String getLoginPage(Model model){
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping(value="/logout-success")
    public String getLogoutPage(Model model){
        model.addAttribute("title", "Logged out!");
        return "logout";
    }

    @GetMapping("/post/{id}")
    public String viewPost(Model model,
        @PathVariable Long id){
        Post post = postDao.getOne(id);
    
        model.addAttribute("title", "Post - " + post.getTitle());
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/manage")
    public String managePosts(Model model){
        List<Post> posts = postDao.findAllByOrderByIdDesc();
        model.addAttribute("title", "Manage Posts");
        model.addAttribute("posts", posts);
        return "managePosts";
    }

    @GetMapping("/post/edit/{id}")
    public String editPostForm(
        @PathVariable Long id,
        Model model){

        Post post = postDao.getOne(id);
        User user = usersSvc.getCurrentUser();

        //allows for admin to edit any post
        if(!usersSvc.postOwner(post, user) || !usersSvc.userIsRole(user, "admin")){ 
            model.addAttribute("error", "You do not own the post");
            return "redirect:/manage";
        };

        model.addAttribute("post", post);
        model.addAttribute("title", "Edit Post - " + post.getTitle());
        return "editPost";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePostForm(
        @PathVariable Long id,
        Model model){

        Post post = postDao.getOne(id);
        User user = usersSvc.getCurrentUser();

        if(!usersSvc.postOwner(post, user) || !usersSvc.userIsRole(user, "admin")){ 
            model.addAttribute("error", "You do not own the post");
            return "redirect:/manage";
        };

        model.addAttribute("post", post);
        model.addAttribute("title", "Delete Post - " + post.getTitle());
        return "deletePost";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost( 
        @PathVariable Long id,
        Model model){
        //create new post
        Post post = postDao.getOne(id);
        //grab user by id and pass it to post
        User user = usersSvc.getCurrentUser();

        //check to see if you are the owner
        if(!usersSvc.postOwner(post, user) || !usersSvc.userIsRole(user, "admin")){ 
            model.addAttribute("error", "You do not own the post");
            return "redirect:/manage";
        };

        postDao.delete(post);
        return "redirect:/manage";
    }

    @PostMapping("/post/edit/{id}")
    public String editPost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "content") String content, 
        @PathVariable Long id,
        Model model){
        //create new post
        Post post = postDao.getOne(id);
        post.setTitle(title);
        post.setContent(content);
        //grab user by id and pass it to post
        User user = usersSvc.getCurrentUser();
        post.setUser(user);
        post.setDateCreated(new Date());
        
        //tags
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("Test", post));
        tags.add(new Tag("Test2", post));

        post.setTags(tags);

        postDao.save(post);
        return "redirect:/manage";
    }

    

    @GetMapping("/post/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewPostForm(){
        return "createPost";
    }

    @PostMapping(path = "/post/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createPost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "content") String content, 
        Model model) {
        //create new post
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        //grab user by id and pass it to post
        User user = usersSvc.getCurrentUser();
        post.setUser(user);
        post.setDateCreated(new Date());
        
        //tags
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("Test", post));
        tags.add(new Tag("Test2", post));

        post.setTags(tags);

        postDao.save(post);
        model.addAttribute("title", "Create Post");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }

    @PostMapping(path = "/register")
    public String createUser(
        @RequestParam(name = "username") String username,
        @RequestParam(name = "email") String email,  
        Model model) {
        //create new post
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
    
        model.addAttribute("title", "Register");
        userDao.save(user);
        return "register";
    }
    
    

    @GetMapping("/")
    public String welcome(Model model) {

        List<Post> posts = postDao.findAllByOrderByIdDesc();
        
        model.addAttribute("title", "Home");
        model.addAttribute("posts", posts);
        return "home";
    }
}

