package com.nznext.geargrove.orders.service;

import com.nznext.geargrove.orders.dto.CreateNewOrderDto;
import com.nznext.geargrove.orders.entity.Order;
import com.nznext.geargrove.orders.exception.UserMustBeAuthenticatedException;
import com.nznext.geargrove.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Service for managing orders in the GearGrove application.
 *
 * <p>This service provides methods to create new orders and handle other order-related operations.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Service} - Marks this class as a Spring service, making it eligible for dependency injection.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required final fields, enabling dependency injection of the {@link OrderRepository}.</li>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    /** The repository for accessing orders in the database. */
    private final OrderRepository orderRepository;

    /**
     * Creates a new order.
     *
     * <p>This method takes the order data from the {@link CreateNewOrderDto} object, validates the user's
     * authentication, and creates a new order. If the user is not authenticated, a {@link UserMustBeAuthenticatedException}
     * is thrown. The created order is saved to the database using the {@link OrderRepository}.</p>
     *
     * @param createNewOrderDto the data for creating the new order.
     * @return the created {@link Order} object if successful, or {@code null} if the order creation fails.
     * @throws UserMustBeAuthenticatedException if the user is not authenticated.
     */
    public Order createNewOrder(CreateNewOrderDto createNewOrderDto) {
        return Stream.of(createNewOrderDto)
                .map(dto -> {
                    Order order = new Order();

                    order.setOrderDate(dto.getOrderDate());
                    order.setStatus(dto.getStatus());
                    order.setSum(dto.getSum());

                    // Ensure the user is authenticated before setting the username
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
                .orElse(null); // If the order creation process fails, return null
    }

    /**
     * Retrieves the current authenticated user's username.
     *
     * <p>This method checks if the user is authenticated and returns their username. If the user is not authenticated,
     * it returns {@code null}.</p>
     *
     * @return the username of the current authenticated user, or {@code null} if the user is not authenticated.
     */
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername(); // Get the username from the UserDetails object
            } else {
                return principal.toString(); // Fallback if the principal is not of type UserDetails
            }
        }
        return null; // Return null if the user is not authenticated
    }
}