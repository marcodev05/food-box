package com.tsk.tools.mapper;

import com.tsk.domain.dto.category.CategoryDtoRequest;
import com.tsk.domain.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Category fromRequestToCategory(CategoryDtoRequest requestDto);
}
