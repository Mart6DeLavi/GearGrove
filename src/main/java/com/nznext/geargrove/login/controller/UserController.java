package com.nznext.geargrove.login.controller;

import com.nznext.geargrove.login.dto.UserAuthDto;
import com.nznext.geargrove.login.dto.UserRegistrationDto;
import com.nznext.geargrove.login.service.AuthService;
import com.nznext.geargrove.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing user-related operations in the GearGrove application.
 *
 * <p>This controller exposes endpoints for user registration, authentication, and deletion.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>User registration via POST {@code /create}.</li>
 *   <li>User login via POST {@code /login} to create an authentication token.</li>
 *   <li>User deletion via DELETE {@code /delete{userId}}.</li>
 * </ul>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @RestController} - Marks this class as a REST controller that will handle HTTP requests.</li>
 *   <li>{@code @RequestMapping} - Maps HTTP requests to the appropriate methods in this controller.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor with the required fields
 *   (i.e., dependencies for {@link UserService} and {@link AuthService}).</li>
 * </ul>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class UserController {

    /** The service for managing user-related operations. */
    private final UserService userService;

    /** The service for handling user authentication and registration. */
    private final AuthService authService;

    /**
     * Endpoint to create a new user.
     *
     * <p>This method takes a {@link UserRegistrationDto} object in the request body,
     * which contains user registration details, and passes it to the {@link AuthService}
     * to handle the creation of the new user.</p>
     *
     * @param user the {@link UserRegistrationDto} containing the user's registration data.
     * @return a {@link ResponseEntity} with a response indicating the success or failure of user creation.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDto user) {
        return authService.createNewUser(user);
    }

    /**
     * Endpoint to log in a user and generate an authentication token.
     *
     * <p>This method takes a {@link UserAuthDto} object in the request body, which contains
     * the user's authentication credentials. The {@link AuthService} processes these credentials
     * and generates an authentication token if the credentials are valid.</p>
     *
     * @param user the {@link UserAuthDto} containing the user's authentication credentials.
     * @return a {@link ResponseEntity} with the generated authentication token or an error message.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final UserAuthDto user) {
        return authService.createAuthToken(user);
    }

    /**
     * Endpoint to delete a user by their user ID.
     *
     * <p>This method takes a user ID in the path and calls the {@link UserService} to delete
     * the user with the corresponding ID.</p>
     *
     * @param userId the ID of the user to be deleted.
     */
    @DeleteMapping("/delete{userId}")
    public void deleteUser(@PathVariable final Long userId) {
        userService.deleteUser(userId);
    }
}