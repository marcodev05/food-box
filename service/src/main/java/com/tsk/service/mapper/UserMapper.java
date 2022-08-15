package com.tsk.service.mapper;

import com.tsk.domain.dto.UserRequest;
import com.tsk.domain.entities.Users;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    Users ToUsers(UserRequest request);
}
