package com.tsk.service.order;

import com.tsk.domain.dto.request.OrderDtoRequest;
import com.tsk.domain.dto.response.OrderDtoResponse;
import com.tsk.domain.entities.OrderEntity;

public interface IOrderService {

    public OrderDtoResponse createOrder(OrderDtoRequest request);

    public OrderEntity fetchAllActiveOrders();

    public Boolean deleteOrder(Long orderId);

    public Boolean validateOrder(Long orderId);
}
