package com.codeup.codeup_demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome(Model model) {

        List<String> tags = new ArrayList<String>();
        tags.add("Testing");
        tags.add("Self");
        
        Post post = new Post("My first post", 1 , "Jacob",tags,"March 25th");
        post.setContent("I would say more but this is just an example post. This is a lot more formal than a twitter microblog and will be used for future employment opptunities");

        List<Post> posts = new ArrayList<Post>();
        posts.add(post);
        posts.add(post);
        posts.add(post);
        posts.add(post);

        model.addAttribute("title", "Home");
        model.addAttribute("posts", posts);
        return "home";
    }
}

//temp deff till JPA is learned
class Post{
    private String title;
    private long id;
    private String author;
    private String content;
    private String date;
    private List<String> tags;

    public Post(String title, long id, String author, List<String> tags, String date){
        this.title = title;
        this.id = id;
        this.author = author;
        this.tags = tags;
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}