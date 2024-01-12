package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserServices service;

    public UserController(UserServices service) {
        this.service = service;
    }

    @GetMapping("user")
    public ResponseEntity<User> showUser() {
        return new ResponseEntity<> (service.getCurrentUser(), HttpStatus.OK);
    }
}
