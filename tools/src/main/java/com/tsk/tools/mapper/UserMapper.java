package com.tsk.tools.mapper;

import com.tsk.domain.dto.UserRequest;
import com.tsk.domain.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    UserEntity ToUsers(UserRequest request);

}
