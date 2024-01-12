package com.boots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RedirectControl {

    @GetMapping("/admin")
    public String showAllUsers() {
        return "admin";
    }
    @GetMapping("/user")
    public String showUser() {
        return "user";
    }
    @GetMapping("/")
    public String showHome() {
        return "Home";
    }

}