package com.tsk.mappers;

import com.tsk.domain.dto.menu.MenuDtoRequest;
import com.tsk.domain.entities.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface MenuMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Menu fromRequestToMenu(MenuDtoRequest requestDto);

}
