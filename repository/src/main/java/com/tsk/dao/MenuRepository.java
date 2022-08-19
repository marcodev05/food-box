package com.tsk.dao;

import com.tsk.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    public Collection<Menu> findByAvailable(Boolean available);
}
