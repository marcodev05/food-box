package com.tsk.tools.mapper;

import com.tsk.domain.dto.request.OrderDtoRequest;
import com.tsk.domain.dto.response.OrderDtoResponse;
import com.tsk.domain.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

    OrderEntity toOrderEntity(OrderDtoRequest orderDtoRequest);

    @Mapping(target = "paymentMethod", source = "paymentMethod.code")
    OrderDtoResponse fromOrderToDtoResponse(OrderEntity orderEntity);
}
