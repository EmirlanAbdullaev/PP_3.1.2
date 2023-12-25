package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserServices service;

    public UserController(UserServices service) {
        this.service = service;
    }

    @GetMapping()
    public String showUserInfo(Model model, Principal principal) {
        User user = service.getUser(principal.getName());
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        model.addAttribute("userRoles", roles);
        model.addAttribute("userAuth", user);
        return "index";
    }
}
