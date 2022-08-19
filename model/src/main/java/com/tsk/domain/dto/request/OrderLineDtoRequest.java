package com.tsk.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class OrderLineDtoRequest {

    @NotBlank(message = "Menu is required")
    private Long menuId;

    @NotBlank(message = "Quantity is required")
    private Integer quantity;
}
