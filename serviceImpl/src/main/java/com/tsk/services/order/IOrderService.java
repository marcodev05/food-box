package com.tsk.services.order;

import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.domain.entities.OrderEntity;

public interface IOrderService {

    public OrderDtoResponse createOrder(OrderDtoRequest request);

    public OrderEntity fetchAllActiveOrders();

    public Boolean deleteOrder(Long orderId);

    public Boolean validateOrder(Long orderId);
}
