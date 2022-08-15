package com.tsk.dao;

import com.tsk.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {

    public Boolean existsByEmail(String email);
}
