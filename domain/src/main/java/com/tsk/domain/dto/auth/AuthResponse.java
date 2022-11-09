package com.tsk.domain.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private List<String> roles;

    private String picture;
}
