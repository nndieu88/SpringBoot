package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Orders;
import com.smartosc.mobile.exception.InternalServerException;
import com.smartosc.mobile.exception.NotFoundException;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.repository.OrdersRepository;
import com.smartosc.mobile.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class OrdersSreviceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Paging findAllOrder(int page) {
        Page<Orders> orders = ordersRepository.findAll(PageRequest.of(page, 8, Sort.by("dateOrder").descending()));

        Paging paging = new Paging();
        paging.setContent(orders.getContent());
        int totalPage = (orders.getTotalPages() == 0 ? 1 : orders.getTotalPages());
        paging.setTotalPage(totalPage);
        paging.setCurrentPage(page + 1);
        paging.setHasNext(orders.hasNext());
        paging.setHasPrev(orders.hasPrevious());
        return paging;
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
    public void createOrder(Orders order) {
        order.setDateOrder(new Date());
        try {
            ordersRepository.save(order);
        } catch (Exception ex) {
            throw new InternalServerException("Can't save order");
        }
    }

    @Override
    public Orders updateOrder(Long id, Orders order) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
