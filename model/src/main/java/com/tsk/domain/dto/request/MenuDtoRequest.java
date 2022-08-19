package com.tsk.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class MenuDtoRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Price is required")
    private Double price;

    @NotBlank(message = "Quantity is required")
    private Integer quantity;

    private String picture;

    private Boolean available;

    private Long categoryId;
}
