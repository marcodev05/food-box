package com.tsk.dao;

import com.tsk.domain.entities.Category;
import com.tsk.domain.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
