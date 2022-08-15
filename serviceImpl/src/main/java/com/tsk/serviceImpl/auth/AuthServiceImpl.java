package com.tsk.serviceImpl.auth;


import com.tsk.dao.RoleRepository;
import com.tsk.dao.UserRepository;
import com.tsk.domain.dto.LoginRequest;
import com.tsk.domain.dto.LoginResponse;
import com.tsk.domain.dto.UserRequest;
import com.tsk.domain.entities.Roles;
import com.tsk.domain.entities.Users;
import com.tsk.security.config.jwt.JwtUtils;
import com.tsk.service.auth.IAuthService;
import com.tsk.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper mapper;

    @Override
    public Users register(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already used");
        } else {
            try {
                Users users = mapper.ToUsers(request);
                users.setRoles(List.of(roleRepository.findByName("ROLE_USER")));
                users.setPassword(passwordEncoder.encode(request.getPassword()));
                return userRepository.save(users);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    @Override
    public Users getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Users getByEmailAndPassword(String email, String password) {
        Users u = this.getByEmail(email);
        if (u != null) {
            if (passwordEncoder.matches(password, u.getPassword())) {
                return u;
            }
        }
        return null;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        Users user = getByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = jwtUtils.generateAccessToken(user);
            LoginResponse response = new LoginResponse(token);
            return response;
        }
        return null;
    }


    @PostConstruct
    private void initRole(){
        roleRepository.save(new Roles(null, "ROLE_USER"));
        roleRepository.save(new Roles(null, "ROLE_ADMIN"));
        roleRepository.save(new Roles(null, "ROLE_DELIVERER"));
    }
}
