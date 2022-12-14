package com.tsk.services.orderline;

import com.tsk.domain.dto.orderline.OrderLineDtoRequest;
import com.tsk.domain.entities.OrderLine;

public interface IOrderLineService {

    public OrderLine createOrderLine(OrderLineDtoRequest orderLineDtoRequest);

    public void deleteOrderLine(Long orderLineId);

}
