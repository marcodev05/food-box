package com.tsk.domain.dto;

import com.tsk.domain.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private String firstname;

    private String lastname;

    private String email;

    private List<Roles> roles;

}
