package com.tsk.serviceImpl.auth;


import com.tsk.dao.RoleRepository;
import com.tsk.dao.UserRepository;
import com.tsk.domain.dto.LoginRequest;
import com.tsk.domain.dto.AuthResponse;
import com.tsk.domain.dto.UserRequest;
import com.tsk.domain.entities.RoleEntity;
import com.tsk.domain.entities.UserEntity;
import com.tsk.exception.ResourceNotFoundException;
import com.tsk.security.config.CustomUserDetails;
import com.tsk.security.config.jwt.JwtUtils;
import com.tsk.service.auth.IAuthService;
import com.tsk.tools.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthServiceImpl implements IAuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper mapper;

    @Override
    public AuthResponse register(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already used");
        } else {
            try {
                UserEntity userEntity = mapper.ToUsers(request);
                userEntity.setRoles(List.of(roleRepository.findByName("USER")));
                userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
                UserEntity createdUser = userRepository.save(userEntity);

                String token = jwtUtils.generateAccessToken(createdUser);
                AuthResponse response = new AuthResponse();
                response.setId(createdUser.getUserId());
                response.setToken(token);
                response.setEmail(createdUser.getEmail());
                response.setFirstname(createdUser.getFirstname());
                response.setLastname(createdUser.getLastname());
                List<String> roles = createdUser.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toList());
                response.setRoles(roles);
                return response;
            } catch (Exception ex) {
                return null;
            }
        }
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserEntity getByEmailAndPassword(String email, String password) {
        UserEntity u = this.getByEmail(email);
        if (u != null) {
            if (passwordEncoder.matches(password, u.getPassword())) {
                return u;
            }
        }
        return null;
    }


    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity user = getByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user != null) {
            String token = jwtUtils.generateAccessToken(user);
            AuthResponse response = new AuthResponse();
            response.setId(user.getUserId());
            response.setToken(token);
            response.setEmail(user.getEmail());
            response.setFirstname(user.getFirstname());
            response.setLastname(user.getLastname());
            List<String> roles = user.getRoles().stream()
                    .map(r -> r.getName())
                    .collect(Collectors.toList());
            response.setRoles(roles);
            return response;
        }
        return null;
    }


    @Override
    public Boolean addRoleToUser(Long userId, Integer roleId) {
        UserEntity user = fetchById(userId);
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("role not found"));
        if (user != null && role != null) {
            user.getRoles().add(role);
            return true;
        }
        return false;
    }


   @Override
    public UserEntity getCurrentUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getByEmail(((CustomUserDetails) user).getUsername());
    }


    private UserEntity fetchById(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return user;
    }


    @PostConstruct
    private void initRole(){
        roleRepository.save(new RoleEntity(null, "USER"));
        RoleEntity r = roleRepository.save(new RoleEntity(null, "ADMIN"));
        roleRepository.save(new RoleEntity(null, "DELIVERER"));
        UserEntity defaultAdmin = new UserEntity();
        defaultAdmin.setEmail("admin@gmail.com");
        defaultAdmin.setPassword(passwordEncoder.encode("12345678"));
        defaultAdmin.setFirstname("Admin");
        defaultAdmin.setLastname("Default");
        defaultAdmin.getRoles().add(r);
        userRepository.save(defaultAdmin);
    }
}
