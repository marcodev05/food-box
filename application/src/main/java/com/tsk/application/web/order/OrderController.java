package com.tsk.application.web.order;

import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.service.order.IOrderService;
import com.tsk.serviceImpl.order.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tsk.application.utils.Constants.*;

@RestController
public class OrderController {

    private IOrderService iOrderService;

    public OrderController(OrderServiceImpl orderService){
        this.iOrderService = orderService;
    }

    @PostMapping(URL_USER + "/orders/add")
    public ResponseEntity<OrderDtoResponse> addMenu(@RequestBody OrderDtoRequest request) {
        OrderDtoResponse order = iOrderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
