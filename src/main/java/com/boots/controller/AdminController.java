package com.boots.controller;

import com.boots.entity.Role;
import com.boots.entity.User;
import com.boots.service.RoleServices;
import com.boots.service.UserServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServices userServices;
    private final RoleServices roleServices;

    public AdminController(RoleServices roleServices, UserServices userService) {
        this.roleServices = roleServices;
        this.userServices = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        model.addAttribute("users", userServices.getUsersList());
        model.addAttribute("userRoles", roles);
        model.addAttribute("userAuth", userServices.getUser(principal.getName()));
        model.addAttribute("listRoles", roleServices.getAllRoles());
        model.addAttribute("newUser", new User());
        return "index";
    }
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user
            , @RequestParam("selectedRoles") List<Long> selectRoles
            , BindingResult result) {

        if (!result.hasErrors()) {
            Set<Role> rolesByArrayIds = roleServices.findAllRoleId(selectRoles);
            userServices.addUser(user, rolesByArrayIds);
        }
        return "redirect:/admin";
    }
    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") User user
            , @RequestParam("selectedRoles") List<Long> selectRoles
            , BindingResult result) {

        if (!result.hasErrors()) {
            Set<Role> rolesByArrayIds = roleServices.findAllRoleId(selectRoles);
            userServices.updateUser(user, rolesByArrayIds);
        }
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userServices.deleteUser(id);
        return "redirect:/admin";
    }
}
