package com.codeup.codeup_demo.controllers;

import javax.validation.Valid;

import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repositories.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userDao;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
        @Valid User user,
        BindingResult bindingResult) {

            if (bindingResult.hasErrors()) {
                return "register";
            }

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(11);

            String hash = bcrypt.encode(user.getPassword());
            user.setPassword(hash);
            userDao.save(user);
            return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
        
    }

}
