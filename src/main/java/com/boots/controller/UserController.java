package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    @GetMapping("/")
    public String getHome(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("login", login);
        return "index";
    }

    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "/profile";
    }

    @Autowired
    private UserServices userService;
}
