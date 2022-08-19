package com.tsk.service.orderline;

import com.tsk.domain.dto.request.OrderLineDtoRequest;
import com.tsk.domain.entities.OrderLine;

public interface IOrderLineService {

    public OrderLine createOrderLine(OrderLineDtoRequest orderLineDtoRequest);

    public void deleteOrderLine(Long orderLineId);

}
