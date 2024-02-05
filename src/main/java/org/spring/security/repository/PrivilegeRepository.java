package org.spring.security.repository;

import org.spring.security.beans.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    public Privilege findByName(String name);
}
