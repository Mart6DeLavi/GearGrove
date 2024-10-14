package com.nznext.geargrove.orders.services;

import com.nznext.geargrove.orders.dtos.CreateNewOrderDto;
import com.nznext.geargrove.orders.entities.Order;
import com.nznext.geargrove.orders.exceptions.UserMustBeAuthenticatedException;
import com.nznext.geargrove.orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createNewOrder(CreateNewOrderDto createNewOrderDto) {
        return Stream.of(createNewOrderDto)
                .map(dto -> {
                    Order order = new Order();

                    order.setOrderDate(dto.getOrderDate());
                    order.setStatus(dto.getStatus());
                    order.setSum(dto.getSum());
                    Optional.ofNullable(getCurrentUsername())
                            .ifPresentOrElse(
                                    order::setUsername,
                                    () -> {
                                        throw new UserMustBeAuthenticatedException("User must be authenticated to create new order");
                                    }
                            );
                    return order;
                })
                .findFirst()
                .map(orderRepository::save)
                .orElse(null);
    }



    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }
}
