package org.spring.security.repository;

import org.spring.security.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName(String name);
}
