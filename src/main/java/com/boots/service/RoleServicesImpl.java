package com.boots.service;

import com.boots.entity.Role;
import com.boots.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServicesImpl implements RoleServices {
    private final RoleRepository roleRepository;
    @PersistenceContext
    EntityManager entityManager;

    public RoleServicesImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    @Transactional
    public Set<Role> findAllRoleId(List<Long> ids) {
        return roleRepository.findAllId(ids);
    }
}
