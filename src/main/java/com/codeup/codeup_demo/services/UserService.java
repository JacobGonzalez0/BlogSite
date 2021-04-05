package com.codeup.codeup_demo.services;

import java.util.List;

import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.models.AuthGroup;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.principals.UserPrincipal;
import com.codeup.codeup_demo.repositories.AuthGroupRepository;
import com.codeup.codeup_demo.repositories.UserRepository;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public UserService(UserRepository userRepository, AuthGroupRepository authGroupRepository){
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        //if we cant find our user we throw this
        if(null==user){
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        //now we check the auth groups 
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new UserPrincipal(user, authGroups);
    }
    
    public boolean userIsRole(User user,String role){
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(user.getUsername());

        return authGroups.stream().anyMatch(group -> 
            group.getAuthGroup().toLowerCase().contains(role));
    }

    public boolean isLoggedIn(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }

    public User getCurrentUser(){
        if(!isLoggedIn()) return null;
        //grabs the user principle that has these methods
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //then abstracts the user and gets it from the DB
        return userRepository.getOne(user.getUser().getId());
    }

    public boolean postOwner(Post post, User user){
        return post.getOwner().equals(user);
    }
    
}
