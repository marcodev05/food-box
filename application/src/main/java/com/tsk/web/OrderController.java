package com.tsk.web;

import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.services.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.tsk.web.utils.Constants.*;

@RestController
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @PostMapping(URL_USER + "/orders/add")
    public ResponseEntity<OrderDtoResponse> addMenu(@RequestBody OrderDtoRequest request) {
        OrderDtoResponse order = iOrderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
