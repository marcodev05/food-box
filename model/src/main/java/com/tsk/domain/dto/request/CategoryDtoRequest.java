package com.tsk.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoRequest {

    @NotBlank(message = "Name of category is required")
    private String name;

    private String description;

    private String picture;
}
