package com.tsk.serviceImpl.order;

import com.tsk.dao.ContactRepository;
import com.tsk.dao.OrderRepository;
import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.domain.entities.*;
import com.tsk.domain.entities.enumeration.EStatus;
import com.tsk.mappers.ContactMapper;
import com.tsk.mappers.OrderMapper;
import com.tsk.services.auth.IAuthService;
import com.tsk.services.order.IOrderService;
import com.tsk.services.orderline.IOrderLineService;
import com.tsk.services.payment_method.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    IOrderLineService iOrderLineService;

    @Autowired
    IAuthService iAuthService;

    @Autowired
    IPaymentMethodService iPaymentMethodService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public OrderDtoResponse createOrder(OrderDtoRequest request) {
        OrderEntity orderEntity = new OrderEntity();

        /***** create order line *******/
        Collection<OrderLine> orderLines = getOrderLinesToOrder(request);
        orderEntity.setOrderLines(orderLines);

        /***** calculate order mount ****/
        double total = getTotalOrder(orderLines);
        orderEntity.setTotal(total);

        /***** payment methode *********/
        PaymentMethod method = iPaymentMethodService.getMethodByCode(request.getPaymentCode());
        orderEntity.setPaymentMethod(method);

        /***** execute BY User *********/
        UserEntity currentUser = iAuthService.getCurrentUser();
        orderEntity.setUserEntity(currentUser);

        /***** Delivery address *******/
        Contact contact = getContactOfRecipient(request, currentUser);
        orderEntity.setContact(contact);

        orderEntity.setCreatedAt(new Date());
        OrderEntity createdOrder = orderRepository.save(orderEntity);
        return orderMapper.fromOrderToDtoResponse(createdOrder);
    }

    @Override
    public List<OrderDtoResponse> fetchOrdersByStatus(String status) {
        List<OrderEntity> orderEntities = orderRepository.findByStatus(EStatus.valueOf(status.toUpperCase()));
        return orderMapper.getOrderDtoResponses(orderEntities);
    }

    @Override
    public List<OrderDtoResponse> fetchAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderMapper.getOrderDtoResponses(orderEntities);
    }

    @Override
    public void confirmAnOrder(Long orderId) {
        try {
            OrderEntity order = orderRepository.findById(orderId).get();
            order.setStatus(EStatus.IN_PROCESS);

            // traitement envoie de notification

            orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Operation failed");
        }
    }

    @Override
    public void declineOrder(Long orderId) {
        try {
            OrderEntity order = orderRepository.findById(orderId).get();
            order.setStatus(EStatus.DECLINED);
            orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Operation failed");
        }
    }

    @Override
    public void deliverAnOrder(Long orderId, Long userId) {
        try {
            OrderEntity order = orderRepository.findById(orderId).get();
            order.setStatus(EStatus.CHIPPING);
            orderRepository.save(order);

            // traitement des données du livreur ici


        } catch (Exception e) {
            throw new RuntimeException("Operation failed");
        }
    }


    @Override
    public Boolean deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public Boolean validateOrder(Long orderId) {
        return null;
    }


    private static double getTotalOrder(Collection<OrderLine> orderLines) {
        double total = 0.0;
        for (OrderLine ln : orderLines) {
            total = total + ln.getMount();
        }
        return total;
    }


    private Contact getContactOfRecipient(OrderDtoRequest request, UserEntity currentUser) {
        Contact contact;
        if (request.getContact() == null) {
            contact = currentUser.getContact();
        } else {
            contact = contactMapper.fromContactDtoToContact(request.getContact());
            contact = contactRepository.save(contact);
        }
        return contact;
    }


    private Collection<OrderLine> getOrderLinesToOrder(OrderDtoRequest request) {
        Collection<OrderLine> orderLines = new ArrayList<OrderLine>();
        request.getOrderLines().forEach((line -> {
            OrderLine o = iOrderLineService.createOrderLine(line);
            orderLines.add(o);
        }));
        return orderLines;
    }

}
