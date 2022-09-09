package com.tsk.services.auth;


import com.tsk.domain.dto.auth.AuthResponse;
import com.tsk.domain.dto.auth.LoginRequest;
import com.tsk.domain.dto.auth.UserRequest;
import com.tsk.domain.entities.UserEntity;

public interface IAuthService {

    public AuthResponse register(UserRequest request);

    public UserEntity getByEmail(String email);

    public UserEntity getByEmailAndPassword(String email, String password);

    public AuthResponse login(LoginRequest request);

    public Boolean addRoleToUser(Long userId, Integer roleId);

    public UserEntity getCurrentUser();
}
