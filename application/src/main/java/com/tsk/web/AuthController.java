package com.tsk.web;


import com.tsk.domain.dto.auth.LoginRequest;
import com.tsk.domain.dto.auth.AuthResponse;
import com.tsk.domain.dto.auth.UserRequest;

import com.tsk.services.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
@RestController
public class AuthController {

    /**
     * Available Users for Role: GET /role/{rol_uid}/available-users
     * Assign User to Role: POST /role/{rol_uid}/user
     */

    @Autowired
    private IAuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRequest request) {
        AuthResponse user = service.register(request);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = service.login(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/admin/role/{role}/user/{id}")
    public void assignUserToRole(@PathVariable("id") Long userId, @PathVariable("role") Integer roleId){
        Boolean response = service.addRoleToUser(userId, roleId);
        if(!response){
            throw new RuntimeException("Operation failed");
        }
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        // generate a new access token
    }


}
