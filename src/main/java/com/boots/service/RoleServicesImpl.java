package com.boots.service;

import com.boots.entity.Role;
import com.boots.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class RoleServicesImpl implements RoleServices {
    @PersistenceContext
    private EntityManager entityManager;
    private final RoleRepository roleRepository;

    public RoleServicesImpl(RoleRepository roleRepository, EntityManager entityManager) {
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Set<Role> findAllRoleId(List<Long> ids) {
        String jpql = "SELECT r FROM Role r WHERE r.id IN :ids";
        return new HashSet<>(entityManager.createQuery(jpql, Role.class)
                .setParameter("ids", ids)
                .getResultList());
    }
}
