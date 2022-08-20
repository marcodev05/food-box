package com.tsk.domain.dto.order;

import com.tsk.domain.dto.contact.ContactDto;
import com.tsk.domain.entities.OrderLine;
import com.tsk.domain.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoResponse {

    private Long orderId;

    private Collection<OrderLine> orderLines;

    private PaymentMethod paymentMethod;

    private ContactDto contact;

    private Double total;
}
