package com.tsk.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private String firstname;

    private String lastname;

    private String email;

    private String address1;

    private String address2;

    private String token;
}
