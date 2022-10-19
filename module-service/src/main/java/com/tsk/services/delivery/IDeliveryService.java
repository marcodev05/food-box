package com.tsk.services.delivery;

import com.tsk.domain.dto.auth.AuthResponse;
import com.tsk.domain.dto.auth.UserRequest;
import com.tsk.domain.entities.Deliverer;
import com.tsk.domain.entities.UserEntity;

import java.util.List;

public interface IDeliveryService {

    List<Deliverer> fetchAllDeliverer();

    List<Deliverer> fetchAllAvailableDeliverer();

    AuthResponse addDeliverer(UserRequest userRequest);


}
