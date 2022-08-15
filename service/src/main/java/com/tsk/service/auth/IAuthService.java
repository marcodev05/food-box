package com.tsk.service.auth;

import com.tsk.domain.dto.LoginRequest;
import com.tsk.domain.dto.LoginResponse;
import com.tsk.domain.dto.UserRequest;
import com.tsk.domain.entities.Users;

public interface IAuthService {

    public Users register(UserRequest request);

    public Users getByEmail(String email);

    public Users getByEmailAndPassword(String email, String password);

    public LoginResponse login(LoginRequest request);
}
