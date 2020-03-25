package com.smartosc.mobile.controller.api;

import com.smartosc.mobile.entity.Orders;
import com.smartosc.mobile.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admins/order")
@RestController
public class ApiOrderController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody Orders orders) {
        ordersService.createOrder(orders);
        return ResponseEntity.ok("successfull");
    }
}
