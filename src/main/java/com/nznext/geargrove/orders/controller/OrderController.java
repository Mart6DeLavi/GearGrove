package com.nznext.geargrove.orders.controller;

import com.nznext.geargrove.orders.dto.CreateNewOrderDto;
import com.nznext.geargrove.orders.entity.Order;
import com.nznext.geargrove.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing order-related operations in the GearGrove application.
 *
 * <p>This controller exposes an endpoint for creating new orders. It handles HTTP requests related to orders
 * and delegates the business logic to the {@link OrderService}.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @RestController} - Marks this class as a REST controller, meaning the response body will be
 *   automatically serialized to JSON or XML (depending on the client).</li>
 *   <li>{@code @RequestMapping} - Specifies the base URL path for the endpoints in this controller ({@code /orders}).</li>
 *   <li>{@code @RequiredArgsConstructor} - Automatically generates a constructor for all required final fields,
 *   enabling dependency injection of the {@link OrderService}.</li>
 * </ul>
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    /** The service responsible for managing orders. */
    private final OrderService orderService;

    /**
     * Creates a new order.
     *
     * <p>This method accepts a {@link CreateNewOrderDto} object in the request body, which contains the details
     * of the new order. It delegates the creation of the order to the {@link OrderService} and returns the created
     * {@link Order} object.</p>
     *
     * @param createNewOrderDto the data required to create a new order.
     * @return the created {@link Order} object.
     */
    @PostMapping("/create")
    public Order createNewOrder(@RequestBody CreateNewOrderDto createNewOrderDto) {
        return orderService.createNewOrder(createNewOrderDto);
    }
}
