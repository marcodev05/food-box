package com.tsk.dao;

import com.tsk.domain.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

    public Roles findByName(String name);
}
