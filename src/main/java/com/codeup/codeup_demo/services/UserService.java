package com.codeup.codeup_demo.services;

import org.springframework.stereotype.Service;

import com.codeup.codeup_demo.repos.UserRepository;


@Service("usersService")
public class UserService {
    
    UserRepository usersRepository;

    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


}
