package com.boots.controller;

import com.boots.entity.Role;
import com.boots.entity.User;
import com.boots.service.RoleServices;
import com.boots.service.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServices userServices;
    private final RoleServices roleServices;

    public AdminController(RoleServices roleServices, UserServices userService) {
        this.roleServices = roleServices;
        this.userServices = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userServices.getUsersList());
        return "admin/admin";
    }

    @GetMapping("/new")
    public String getCreateNewUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleServices.getAllRoles());
        return "admin/new_user";
    }

    @PostMapping(value = "/")
    public String add(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Model model, @RequestParam(value = "ids", required = false) List<Long> ids) {

            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);

            userServices.saveUser(user);
            return "redirect:/admin";


    }

    @GetMapping("/deleteUser")
    public String removeUser(@RequestParam("id") int id) {
        userServices.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String getEditUserForm(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userServices.getUser(id));
        model.addAttribute("allRoles", roleServices.getAllRoles());
        return "admin/edit_user";
    }

    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Model model, @RequestParam(value = "ids", required = false) List<Long> ids) {

            Set<Role> assignedRole = roleServices.findAllRoleId(ids);
            user.setRoles(assignedRole);
            userServices.saveUser(user);
            return "redirect:/admin";
    }
}
