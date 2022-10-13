package com.tsk.mappers;

import com.tsk.domain.dto.orderline.OrderLineDtoRequest;
import com.tsk.domain.entities.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderLineMapper {
    @Mapping(target = "menu.menuId", source = "menuId")
    OrderLine toOrderLine(OrderLineDtoRequest orderLineDtoRequest);
}
