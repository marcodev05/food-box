package com.tsk.domain.dto.response;

import com.tsk.domain.entities.OrderLine;
import com.tsk.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoResponse {

    private Long orderId;

    private Collection<OrderLine> orderLines;

    private PaymentMethod paymentMethod;

    private Double total;
}
