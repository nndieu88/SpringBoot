package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Orders;
import com.smartosc.mobile.exception.NotFoundException;
import com.smartosc.mobile.repository.OrdersRepository;
import com.smartosc.mobile.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrdersSreviceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> findAllOrder() {
        List<Orders> orders = ordersRepository.findAll();
        return orders;
    }

    @Override
    public Optional<Orders> findById(Long id) {
        Optional<Orders> orders = ordersRepository.findById(id);
        if (!orders.isPresent()) {
            throw new NotFoundException("Not found order");
        }
        return orders;
    }

    @Override
    public Orders createOrder(Orders order) {

        return null;
    }

    @Override
    public Orders updateOrder(Long id, Orders order) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
