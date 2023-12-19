package com.boots.repository;

import com.boots.entity.Role;
import com.boots.service.RoleServicesImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
