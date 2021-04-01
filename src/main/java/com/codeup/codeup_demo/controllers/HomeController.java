package com.codeup.codeup_demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
public class HomeController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final UserService usersSvc;
    private final BCryptPasswordEncoder bcrypt;


    public HomeController(UserRepository userDao, PostRepository postDao, UserService usersSvc) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.usersSvc = usersSvc;
        this.bcrypt = new BCryptPasswordEncoder(11);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String managePosts(Model model){
        List<Post> posts = postDao.findAllByOrderByIdDesc();
        model.addAttribute("title", "Manage Posts");
        model.addAttribute("posts", posts);
        return "managePosts";
    }

    @GetMapping("/post/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPostForm(
        @PathVariable Long id,
        Model model){

        Post post = postDao.getOne(id);
        User user = usersSvc.getCurrentUser();
        StringBuilder tags = new StringBuilder();

        //allows for admin to edit any post
        if(!usersSvc.postOwner(post, user) || !usersSvc.userIsRole(user, "admin")){ 
            model.addAttribute("error", "You do not own the post");
            return "redirect:/manage";
        };

        //Comma seperates tags
        for(int i = 0; i < post.getTags().size(); i++ ){
            if(i == 0){
                tags.append(post.getTags().get(i).getName());
            }else{
                tags.append("," + post.getTags().get(i).getName());
            }
        }

        model.addAttribute("post", post);
        model.addAttribute("tags", tags.toString());
        model.addAttribute("title", "Edit Post - " + post.getTitle());
        return "editPost";
    }

    @GetMapping("/post/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "content") String content, 
        @RequestParam(name = "tags") String tagsInput, 
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
        List<String> tagStr = Arrays.asList(tagsInput.split(",", -1));
        tagStr.forEach(tag ->{
            tags.add(new Tag(tag, post));
        });

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
        @RequestParam(name = "tags") String tagsInput, 
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
        List<String> tagStr = Arrays.asList(tagsInput.split(",", -1));
        tagStr.forEach(tag ->{
            tags.add(new Tag(tag, post));
        });

        post.setTags(tags);

        postDao.save(post);
        model.addAttribute("title", "Create Post");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerForm(
        Model model
    ){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(path = "/register")
    public String createUser(
        @Valid User user,
        BindingResult bindingResult,
        Model model) {
        //create new post
        if (bindingResult.hasErrors()) {
            return "register";
        }

        String hash = bcrypt.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        model.addAttribute("message", "You should be able to login now!");
        return "login";
    }
    
    

    @GetMapping("/")
    public String welcome(Model model) {

        List<Post> posts = postDao.findAllByOrderByIdDesc();
        
        model.addAttribute("title", "Home");
        model.addAttribute("posts", posts);
        return "home";
    }
}

