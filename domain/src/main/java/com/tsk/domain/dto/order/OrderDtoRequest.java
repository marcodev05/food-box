package com.tsk.domain.dto.order;

import com.tsk.domain.dto.contact.ContactDto;
import com.tsk.domain.dto.orderline.OrderLineDtoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Component
public class OrderDtoRequest {

    @NotEmpty
    private List<OrderLineDtoRequest> orderLines;

    @NotBlank(message = "Set your payment methode")
    private String paymentCode;

    private ContactDto contact;

    public OrderDtoRequest() {
    }

    public OrderDtoRequest(List<OrderLineDtoRequest> orderLines, String paymentCode, ContactDto contact) {
        this.orderLines = orderLines;
        this.paymentCode = paymentCode;
        this.contact = contact;
    }
}
