package com.boots.config;

import com.boots.entity.Role;
import com.boots.entity.User;
import com.boots.repository.RoleRepository;
import com.boots.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Set;

@Component
public class DateBaseInit {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public DateBaseInit(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initializeDatabase() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User userFirst = new User(passwordEncoder.encode("100"),"Name1","LastName2","admin@gmail.com",(byte)21,Set.of(adminRole,userRole));
        User userSecond = new User(passwordEncoder.encode("100"),"Name1","LastName2","user@gmail.com",(byte)22,Set.of(userRole));

        userRepository.save(userFirst);
        userRepository.save(userSecond);
    }
}
