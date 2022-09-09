package com.tsk.serviceImpl.orderline;

import com.tsk.dao.OrderLineRepository;
import com.tsk.domain.dto.orderline.OrderLineDtoRequest;
import com.tsk.domain.entities.Menu;
import com.tsk.domain.entities.OrderLine;
import com.tsk.exception.BadRequestException;
import com.tsk.mappers.OrderLineMapper;
import com.tsk.services.menu.IMenuService;
import com.tsk.services.orderline.IOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderLineServiceImpl implements IOrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private IMenuService iMenuService;

    @Autowired
    private OrderLineMapper orderLineMapper;

    @Override
    public OrderLine createOrderLine(OrderLineDtoRequest orderLineDtoRequest) {
        OrderLine orderLine = orderLineMapper.toOrderLine(orderLineDtoRequest);
        Double mount = checkStockOfMenu(orderLine);
        orderLine.setMount(mount);
        return orderLineRepository.save(orderLine);
    }

    private Double checkStockOfMenu(OrderLine orderLine) {
        Long menuId = orderLine.getMenu().getMenuId();
        Menu menu = iMenuService.fetchMenuById(menuId);
        Double pu = menu.getPrice();
        Integer stock = menu.getQuantity();
        Integer qty = orderLine.getQuantity();
        Integer restQty;
        double mount = 0.0;
        if (qty <= stock) {
            mount = qty * pu;
            restQty = stock - qty;
            menu.setQuantity(restQty);
            if (restQty == 0) menu.setAvailable(false);
            orderLine.setMenu(menu);
            iMenuService.updateMenu(menu);
            return mount;
        } else throw new BadRequestException("Quantity insufficient !");


    }

    @Override
    public void deleteOrderLine(Long orderLineId) {

    }

}
