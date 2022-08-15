package com.tsk.dao;

import com.tsk.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {

    public Boolean existsByEmail(String email);

    public Users findByEmail(String email);
}
