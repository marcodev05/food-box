package com.tsk.service.mapper;

import com.tsk.dto.auth.UserRequest;
import com.tsk.model.Users;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    Users ToUsers(UserRequest request);
}
