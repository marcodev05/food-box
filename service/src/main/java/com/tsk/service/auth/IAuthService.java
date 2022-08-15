package com.tsk.service.auth;

import com.tsk.dto.auth.UserRequest;
import com.tsk.model.Users;

public interface IAuthService {

    public Users register(UserRequest request);

    public Users getByEmail(String email);

    public Users getByEmailAndPassword(String email, String password);

}
