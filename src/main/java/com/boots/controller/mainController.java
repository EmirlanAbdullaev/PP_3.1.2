package com.boots.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/admin")
    public String currentUser() {
        return "test";
    }
}
