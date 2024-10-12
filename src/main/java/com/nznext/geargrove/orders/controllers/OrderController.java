package com.nznext.geargrove.orders.controllers;

import com.nznext.geargrove.orders.dtos.CreateNewOrderDto;
import com.nznext.geargrove.orders.entities.Order;
import com.nznext.geargrove.orders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public Order createNewOrder(@RequestBody CreateNewOrderDto createNewOrderDto) {
        return orderService.createNewOrder(createNewOrderDto);
    }


}
