package com.boots.service;

import com.boots.entity.User;

import java.util.List;

public interface UserServices {
    public List<User> getUsersList();

    public boolean addUser(User user);

    public User getUser(String name);
    public User getUser(long id);

    public void deleteUser(long id);
    public void update(User user);
}
