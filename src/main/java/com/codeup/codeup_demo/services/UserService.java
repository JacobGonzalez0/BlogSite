package com.codeup.codeup_demo.services;

import com.codeup.codeup_demo.repos.AuthGroupRepository;
import com.codeup.codeup_demo.repos.UserRepository;

import java.util.List;

import com.codeup.codeup_demo.models.AuthGroup;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.principals.UserPrincipal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

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
}
