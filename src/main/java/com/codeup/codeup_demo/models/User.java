package com.codeup.codeup_demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username must exist")
    @Size(min = 4, max = 16, message = "Username must be 4 characters long")
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password must exist")
    @Size(min = 8, max = 30, message = "Password must contain more than 8 characters")
    @Column(name="password")
    private String password;

    @Email
    @Column(name="email")
    private String email;

    public long getId() {
        return id; 
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
