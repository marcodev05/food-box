package com.tsk.dao;

import com.tsk.domain.entities.OrderEntity;
import com.tsk.domain.entities.enumeration.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    public List<OrderEntity> findByStatus(EStatus status);
}
