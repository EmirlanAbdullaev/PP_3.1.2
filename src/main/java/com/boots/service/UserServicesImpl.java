package com.boots.service;

import com.boots.entity.Role;
import com.boots.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.boots.entity.User;
import com.boots.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class UserServicesImpl implements UserServices {


    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(@Lazy BCryptPasswordEncoder passwordEncoder) {
        this.bCryptPasswordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUser(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getOne(id);
    }
    @Transactional
    @Override
    public void updateUser(User user){
        if (null!=userRepository.findByUsername(user.getUsername())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


}
