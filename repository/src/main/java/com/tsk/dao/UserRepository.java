package com.tsk.dao;

import com.tsk.domain.entities.Deliverer;
import com.tsk.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);

    List<Deliverer> findByAvailableTrue();

    @Query("SELECT u FROM UserEntity u WHERE u.class = 'DEL'")
    List<Deliverer> findAllDeliverer();

    UserEntity findByEmail(String email);

    List<UserEntity> findByRoles_Name(String roleName);

}
