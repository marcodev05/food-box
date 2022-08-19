package com.tsk.dao;

import com.tsk.domain.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    public RoleEntity findByName(String name);
}
