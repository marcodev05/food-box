package com.tsk.tools.mapper;

import com.tsk.domain.dto.auth.UserRequest;
import com.tsk.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    UserEntity ToUsers(UserRequest request);
}
