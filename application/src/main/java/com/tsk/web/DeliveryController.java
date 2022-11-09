package com.tsk.web;

import com.tsk.domain.dto.auth.AuthResponse;
import com.tsk.domain.dto.auth.UserRequest;
import com.tsk.domain.entities.Deliverer;
import com.tsk.services.delivery.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tsk.web.utils.Constants.URL_MANAGER;

@CrossOrigin("*")
@RestController
public class DeliveryController {

    @Autowired
    private IDeliveryService iDeliveryService;

    @GetMapping(URL_MANAGER + "/deliverers")
    public ResponseEntity<List<Deliverer>> getAllDeliverer(){
        List<Deliverer> delivers = iDeliveryService.fetchAllDeliverer();
        return new ResponseEntity<>(delivers, HttpStatus.OK);
    }

    @GetMapping(URL_MANAGER + "/deliverers/available")
    public ResponseEntity<List<Deliverer>> getAvailableDeliverer(){
        List<Deliverer> delivers = iDeliveryService.fetchAllAvailableDeliverer();
        return new ResponseEntity<>(delivers, HttpStatus.OK);
    }

    @PostMapping(value = URL_MANAGER + "/deliverers/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthResponse> addDeliverer(@ModelAttribute UserRequest userRequest) {
        AuthResponse response = iDeliveryService.addDeliverer(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
