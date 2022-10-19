package com.tsk.web;

import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.domain.entities.UserEntity;
import com.tsk.services.delivery.IDeliveryService;
import com.tsk.services.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tsk.web.utils.Constants.*;

@CrossOrigin("*")
@RestController
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IDeliveryService iDeliveryService;

    @PostMapping(URL_USER + "/orders/add")
    public ResponseEntity<OrderDtoResponse> addMenu(@RequestBody OrderDtoRequest request) {
        OrderDtoResponse order = iOrderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping(URL_MANAGER + "/orders/status/{status}")
    public ResponseEntity<List<OrderDtoResponse>> fetchOrderByStatus(@PathVariable("status") String status){
        List<OrderDtoResponse> orders = iOrderService.fetchOrdersByStatus(status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping(URL_MANAGER + "/orders/{id}")
    public ResponseEntity<OrderDtoResponse> fetchOrderById(@PathVariable("id") Long id) {
        OrderDtoResponse order = iOrderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }



    @GetMapping(URL_MANAGER + "/orders")
    public ResponseEntity<List<OrderDtoResponse>> fetchAllOrders(){
        List<OrderDtoResponse> orders = iOrderService.fetchAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping(URL_MANAGER + "/orders/{id}/confirm")
    public Map<String, Boolean> validateAnOrder(@PathVariable("id") Long id){
        iOrderService.confirmAnOrder(id);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("confirmed", Boolean.TRUE);
        return response;
    }


    @GetMapping(URL_MANAGER + "/orders/{id}/decline")
    public Map<String, Boolean> declineAnOrder(@PathVariable("id") Long id){
        iOrderService.declineOrder(id);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("declined", Boolean.TRUE);
        return response;
    }


    @GetMapping(URL_MANAGER + "/orders/{id}/assign/{deliverer_id}")
    public Map<String, Boolean> assignAnOrderToDeliverer(@PathVariable("id") Long id,
                                                         @PathVariable("deliverer_id") Long deliverer_id) {
        iOrderService.deliverAnOrder(id, deliverer_id);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("declined", Boolean.TRUE);
        return response;
    }


}
