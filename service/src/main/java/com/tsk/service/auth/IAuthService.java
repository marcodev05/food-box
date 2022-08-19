package com.tsk.service.auth;

import com.tsk.domain.dto.LoginRequest;
import com.tsk.domain.dto.AuthResponse;
import com.tsk.domain.dto.UserRequest;
import com.tsk.domain.entities.UserEntity;

public interface IAuthService {

    public AuthResponse register(UserRequest request);

    public UserEntity getByEmail(String email);

    public UserEntity getByEmailAndPassword(String email, String password);

    public AuthResponse login(LoginRequest request);

    public Boolean addRoleToUser(Long userId, Integer roleId);

    public UserEntity getCurrentUser();
}
