package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAPI {
    private final UserServices service;

    public UserAPI(UserServices service) {
        this.service = service;
    }
    @GetMapping("/authorizedUser")
    @CrossOrigin
    public User showUser() {
        System.out.println(service.getCurrentUser());
        return service.getCurrentUser();
    }
}
