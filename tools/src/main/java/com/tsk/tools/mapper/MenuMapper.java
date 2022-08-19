package com.tsk.tools.mapper;

import com.tsk.domain.dto.request.MenuDtoRequest;
import com.tsk.domain.entities.Menu;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface MenuMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Menu fromRequestToMenu(MenuDtoRequest requestDto);

}