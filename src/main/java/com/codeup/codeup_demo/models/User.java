package com.codeup.codeup_demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is Required")
    @Size(min = 5, max = 16, message = "Username must be between 5-16 characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Email is Required")
    @Email
    private String email;

    // 000-000-0000
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}", message = "Phone number be in 000-000-0000 format")
    private String phone;

    @NotBlank(message = "Password is Required")
    @Size(min = 5, message = "Password must be between 5-16 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    private Image userImage;

    public User(Long id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getUserImage() {
        if(this.userImage == null){
            return new Image(
                "img/noProfile.png"
            );
        }
        return this.userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }



}

