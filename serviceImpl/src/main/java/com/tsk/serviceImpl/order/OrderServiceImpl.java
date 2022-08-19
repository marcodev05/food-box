package com.tsk.serviceImpl.order;

import com.tsk.dao.OrderRepository;
import com.tsk.domain.dto.request.OrderDtoRequest;
import com.tsk.domain.dto.response.OrderDtoResponse;
import com.tsk.domain.entities.OrderEntity;
import com.tsk.domain.entities.OrderLine;
import com.tsk.domain.entities.PaymentMethod;
import com.tsk.domain.entities.UserEntity;
import com.tsk.service.auth.IAuthService;
import com.tsk.service.order.IOrderService;
import com.tsk.service.orderline.IOrderLineService;
import com.tsk.service.payment_method.IPaymentMethodService;
import com.tsk.tools.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    IOrderLineService iOrderLineService;

    @Autowired
    IAuthService iAuthService;

    @Autowired
    IPaymentMethodService iPaymentMethodService;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public OrderDtoResponse createOrder(OrderDtoRequest request) {
        OrderEntity orderEntity = new OrderEntity();

        /***** create order line *******/
        Collection<OrderLine> orderLines = new ArrayList<OrderLine>();
        request.getOrderLines().forEach((line -> {
            OrderLine o = iOrderLineService.createOrderLine(line);
            orderLines.add(o);
        }));
        orderEntity.setOrderLines(orderLines);

        /***** calculate total *********/

        double total = 0.0;
        for (OrderLine ln : orderLines) {
            total = total + ln.getMount();
        }

        orderEntity.setTotal(total);
        /***** payment methode *********/
        PaymentMethod method = iPaymentMethodService.getMethodByCode(request.getPaymentCode());
        orderEntity.setPaymentMethod(method);

        /***** execute BY User *********/
        UserEntity currentUser = iAuthService.getCurrentUser();
        orderEntity.setUserEntity(currentUser);

        orderEntity.setCreatedAt(new Date());
        OrderEntity createdOrder = orderRepository.save(orderEntity);
        return orderMapper.fromOrderToDtoResponse(createdOrder);
    }


    @Override
    public OrderEntity fetchAllActiveOrders() {
        return null;
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public Boolean validateOrder(Long orderId) {
        return null;
    }
}
