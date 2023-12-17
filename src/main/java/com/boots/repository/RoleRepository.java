package com.boots.repository;

import com.boots.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository {
    List<Role> findAll();

    Set<Role> findAllId(List<Long> ids);

    Role getById(Long id);

    void save(Role role);

}
