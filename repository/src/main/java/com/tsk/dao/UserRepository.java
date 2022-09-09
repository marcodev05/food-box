package com.tsk.dao;

import com.tsk.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Boolean existsByEmail(String email);

    public UserEntity findByEmail(String email);
}
