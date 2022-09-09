package com.tsk.domain.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    @NotBlank(message = "firstname is required")
    private String firstname;

    @NotBlank(message = "lastname is required")
    private String lastname;

    @NotBlank(message = "address is required")
    private String address1;

    private String address2;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "phone number is required")
    private String phone;
}
