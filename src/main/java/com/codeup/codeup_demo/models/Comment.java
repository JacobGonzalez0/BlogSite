package com.codeup.codeup_demo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "post_comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timePosted;

    @NotBlank(message = "Must contain text")
    @Size(min = 1, max = 255, message = "Post must not be longer than 255 characters")
    private String text;

    @ManyToOne
    @JoinColumn( name = "post_id")
    private Post post;

    @OneToOne 
    private User owner;

    public Comment() {
    }

    public Comment(Long id, Date timePosted, String text, Post post, User owner) {
        this.id = id;
        this.timePosted = timePosted;
        this.text = text;
        this.post = post;
        this.owner = owner;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimePosted() {
        return this.timePosted;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
