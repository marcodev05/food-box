package com.tsk.controller.auth;


import com.tsk.dto.auth.UserRequest;
import com.tsk.model.Users;

import com.tsk.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {


    @Autowired
    IAuthService service;

    @PostMapping("/register")
    public ResponseEntity<Users> register(@Valid @RequestBody UserRequest request){
        Users user = service.register(request);
        System.out.println(user);
        if (user != null ){
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
