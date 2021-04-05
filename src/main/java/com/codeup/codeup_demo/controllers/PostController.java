package com.codeup.codeup_demo.controllers;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.codeup.codeup_demo.models.Image;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repositories.PostRepository;
import com.codeup.codeup_demo.repositories.UserRepository;
import com.codeup.codeup_demo.services.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

    @Autowired
    UserService usersSvc;
    @Autowired
    UserRepository userDao;
    @Autowired
    PostRepository postDao;

    @RequestMapping(value="/post/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public String getPosts(
        Model model) throws JSONException{
            List<Post> posts = postDao.findAllByOrderByIdDesc();;

            JSONArray entities = new JSONArray();

            for( Post post : posts){

                JSONObject entity = new JSONObject();
                JSONObject user = new JSONObject();
                user.put("username", post.getOwner().getUsername());
                user.put("id", post.getOwner().getId());
                //checks if there is a profile picture or not
                if(post.getOwner().getUserImage().getUrl() == null){
                    user.put("userImageUrl", "img/noProfile.png");
                }else{
                    user.put("userImageUrl", post.getOwner().getUserImage().getUrl());
                }
                JSONArray images = new JSONArray();
                for( Image img : post.getImages()){
                    JSONObject image = new JSONObject();
                    image.put("url", img.getUrl());
                    image.put("id", img.getId());
                    images.put(img);
                }
                entity.put("text", post.getText());
                entity.put("post_id", post.getId());
                entity.put("owner", user); 
                entity.put("images", images);
                entities.put(entity);

            }
        
            return entities.toString();
    }

    @PostMapping("/post/create")
    public String createPost(
        Model model,
        @Valid Post newPost,
        BindingResult bindingResult
        ){
            if (bindingResult.hasErrors()) {
                return "{'error':'Error with post'}";
            }

            User user = usersSvc.getCurrentUser();
            newPost.setOwner(user);

            postDao.save(newPost);
        

            return "home";
    }

    @PostMapping("/post/edit/{id}")
    public String createPost(
        Model model,
        @Valid Post newPost,
        @PathVariable Long id,
        BindingResult bindingResult
        ){
            if (bindingResult.hasErrors()) {
                return "{'error':'Error with post'}";
            }

            User user = usersSvc.getCurrentUser();
            Post post = postDao.getOne(id);

            if(usersSvc.postOwner(post, user)){
                return "{'error':'You do not own the post'}";
            }

            post.setText(newPost.getText());

            postDao.save(newPost);
        

            return "home";
    }
    
}
