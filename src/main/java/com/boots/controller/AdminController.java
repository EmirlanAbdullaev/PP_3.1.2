package com.boots.controller;

import com.boots.entity.Role;
import com.boots.entity.User;
import com.boots.service.RoleServices;
import com.boots.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class AdminController {
    private final UserServices userServices;
    private final RoleServices roleServices;

    public AdminController(RoleServices roleServices, UserServices userService) {
        this.roleServices = roleServices;
        this.userServices = userService;
    }

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<List<User>> showUsers() {

        return new ResponseEntity<>(userServices.getUsersList(), HttpStatus.OK);
    }
    @GetMapping("/getRoles")
    public List<Role> getRoles() {
        return roleServices.getAllRoles();
    }
    @GetMapping("/authorizedUser")
    @CrossOrigin
    public ResponseEntity<?> getAuthorizedUser() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServices.getUser(userDetails.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping()
    @CrossOrigin
    public ResponseEntity<User> create(@RequestBody User user){
//        Set<Role> rolesByArrayIds = roleServices.findAllRoleId(selectRoles);,List<Long> selectRoles
        return new ResponseEntity<>(userServices.addUser(user,new HashSet<>()),HttpStatus.OK);
    }

    @PutMapping()
    @CrossOrigin
    public ResponseEntity<List<User>> updateUser(@RequestBody User user){
        userServices.updateUser(user,new HashSet<>());
        return new ResponseEntity<>(userServices.getUsersList(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public HttpStatus deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return HttpStatus.OK;
    }
}
