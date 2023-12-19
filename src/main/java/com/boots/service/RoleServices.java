package com.boots.service;

import com.boots.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleServices {
    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Set<Role> findAllRoleId(List<Long> ids);
}
