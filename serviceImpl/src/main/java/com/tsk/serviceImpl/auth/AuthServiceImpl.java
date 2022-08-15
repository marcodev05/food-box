package com.tsk.serviceImpl.auth;


import com.tsk.dao.RoleRepository;
import com.tsk.dao.UserRepository;
import com.tsk.dto.auth.UserRequest;
import com.tsk.model.Roles;
import com.tsk.model.Users;
import com.tsk.service.auth.IAuthService;
import com.tsk.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserMapper mapper;

    @Override
    public Users register(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already used");
        } else {
            try {
                Users users = mapper.ToUsers(request);
                users.setRoles(List.of(roleRepository.findByName("ROLE_USER")));
                users.setPassword(request.getPassword());
                return userRepository.save(users);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    @Override
    public Users getByEmail(String email) {
        return null;
    }

    @Override
    public Users getByEmailAndPassword(String email, String password) {
        return null;
    }

    @PostConstruct
    private void initRole(){
        roleRepository.save(new Roles(null, "ROLE_USER"));
        roleRepository.save(new Roles(null, "ROLE_ADMIN"));
        roleRepository.save(new Roles(null, "ROLE_DELIVERER"));
    }
}
