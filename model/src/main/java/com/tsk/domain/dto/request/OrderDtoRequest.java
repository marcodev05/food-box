package com.tsk.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoRequest {

    private List<OrderLineDtoRequest> orderLines;

    @NotBlank(message = "Set your payment methode")
    private String paymentCode;
}
