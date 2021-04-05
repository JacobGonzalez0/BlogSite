package com.codeup.codeup_demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table( name = "profiles")
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @NotBlank(message = "Please enter birthday")
    @Pattern(regexp="^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$",message = "Date must be in dd/mm/yyyy")
    private String birthday;

    @NotBlank(message = "Must decide pronouns")
    @Size(min = 1, max = 120, message = "Must decide pronouns")
    private String gender;

    @Size(min = 1, max = 120, message = "Location can only be 120 characters long")
    private String location;
    

    public Profile() {
    }

    public Profile(Long id, User user, String birthday, String gender, String location) {
        this.id = id;
        this.user = user;
        this.birthday = birthday;
        this.gender = gender;
        this.location = location;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        if(this.location == null){
            return "N/A";
        }
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirthday() {
        if(this.birthday == null){
            return "N/A";
        }
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        if(this.gender == null){
            return "N/A";
        }
        return this.gender;
    }

}
