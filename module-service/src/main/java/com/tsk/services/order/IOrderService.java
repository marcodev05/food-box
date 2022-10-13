package com.tsk.services.order;

import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.domain.entities.OrderEntity;
import com.tsk.domain.entities.enumeration.EStatus;

import java.util.List;

public interface IOrderService {

    public OrderDtoResponse createOrder(OrderDtoRequest request);

    public List<OrderDtoResponse> fetchOrdersByStatus(String status);

    public List<OrderDtoResponse> fetchAllOrders();

    public void confirmAnOrder(Long orderId);

    public void  declineOrder(Long orderId);

    public void deliverAnOrder(Long orderId, Long userId);

    public Boolean deleteOrder(Long orderId);

    public Boolean validateOrder(Long orderId);
}
