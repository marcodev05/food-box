package com.tsk.domain.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDtoRequest {

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

    private MultipartFile file;
}
