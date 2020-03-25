package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrdersService {
    public List<Orders> findAllOrder();

    public Optional<Orders> findById(Long id);

    public void createOrder(Orders order);

    public Orders updateOrder(Long id, Orders order);

    public void deleteOrder(Long id);
}
