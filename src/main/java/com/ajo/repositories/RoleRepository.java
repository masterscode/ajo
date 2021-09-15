package com.ajo.repositories;

import com.ajo.models.Role;
import com.ajo.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByUserRole(UserRole userRole);
}