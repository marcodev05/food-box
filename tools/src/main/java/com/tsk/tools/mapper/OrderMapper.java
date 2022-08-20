package com.tsk.tools.mapper;

import com.tsk.domain.dto.order.OrderDtoRequest;
import com.tsk.domain.dto.order.OrderDtoResponse;
import com.tsk.domain.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

    OrderEntity toOrderEntity(OrderDtoRequest orderDtoRequest);

    @Mapping(target = "paymentMethod", source = "paymentMethod")
    OrderDtoResponse fromOrderToDtoResponse(OrderEntity orderEntity);
}
