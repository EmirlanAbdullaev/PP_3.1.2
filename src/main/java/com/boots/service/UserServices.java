package com.boots.service;

import com.boots.entity.Role;
import com.boots.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserServices extends UserDetailsService {
    public User addUser(User user, Set<Role> roleSet);
    public void updateUser(User user, Set<Role> roleSet);
    public List<User> getUsersList();

    public User getUser(String name);

    public User getUser(long id);

    public void deleteUser(long id);
}
