package com.boots.controller;

import com.boots.entity.Role;
import com.boots.entity.User;
import com.boots.service.RoleServices;
import com.boots.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAPI {
    private final UserServices userServices;
    private final RoleServices roleServices;

    public RestAPI(RoleServices roleServices, UserServices userService) {
        this.roleServices = roleServices;
        this.userServices = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> showUsers() {

        return new ResponseEntity<>(userServices.getUsersList(), HttpStatus.OK);
    }
    @GetMapping("/getRoles")
    public List<Role> getRoles() {
        return roleServices.getAllRoles();
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return new ResponseEntity<> (userServices.getUser(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody User user){
        userServices.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        userServices.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return HttpStatus.OK;
    }
}
