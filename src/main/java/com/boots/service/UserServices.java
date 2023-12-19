package com.boots.service;

import com.boots.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServices extends UserDetailsService {
    public List<User> getUsersList();

    public void saveUser(User user);

    public User getUser(String name);
    public User getUser(long id);

    public void deleteUser(long id);
}
