package com.nznext.geargrove.login.service;

import com.nznext.geargrove.login.dto.UserAuthDto;
import com.nznext.geargrove.login.dto.UserRegistrationDto;
import com.nznext.geargrove.login.entity.Role;
import com.nznext.geargrove.login.entity.UserEntity;
import com.nznext.geargrove.message.EmailSender;
import com.nznext.geargrove.login.repository.RoleRepository;
import com.nznext.geargrove.login.repository.UserEntityRepository;
import com.nznext.geargrove.login.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

/**
 * Service for handling authentication and user registration operations.
 *
 * <p>This service provides methods to authenticate users, generate authentication tokens,
 * and handle user registration. It uses various services such as {@link UserService},
 * {@link JwtTokenUtils}, {@link AuthenticationManager}, and others to perform the required actions.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Service} - Marks this class as a Spring service, making it eligible for dependency injection.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor with required fields for dependency injection.</li>
 *   <li>{@code @Slf4j} - Provides logging functionality for the class.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    /** The service responsible for user-related operations. */
    private final UserService userService;

    /** Utility class for handling JWT token generation and validation. */
    private final JwtTokenUtils jwtTokenUtils;

    /** The authentication manager for managing authentication processes. */
    private final AuthenticationManager authenticationManager;

    /** The repository for accessing user entities. */
    private final UserEntityRepository userEntityRepository;

    /** The repository for accessing roles in the database. */
    private final RoleRepository roleRepository;

    /** The email sender for notifying users of logins. */
    private final EmailSender emailSender;

    /**
     * Authenticates a user and generates a JWT token for them.
     *
     * <p>This method authenticates the user based on their username and password, then generates a JWT token
     * for them using {@link JwtTokenUtils}. The token is sent via a {@link ResponseEntity} to the client.
     * If authentication fails or the user is not found, an appropriate error response is returned.</p>
     *
     * @param userAuthDto the authentication details of the user (username and password).
     * @return a {@link ResponseEntity} containing the generated JWT token if authentication is successful,
     *         or an error response if authentication fails.
     */
    public ResponseEntity<?> createAuthToken(UserAuthDto userAuthDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userAuthDto.getUsername(),
                            userAuthDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return userEntityRepository.findUserEntityByUsername(userAuthDto.getUsername())
                    .map(user -> {
                        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                        String token = jwtTokenUtils.generateUserToken(userDetails, user.getRoles());
                        log.info("Token: {} for username: {} was generated", token, userDetails.getUsername());
                        emailSender.sendEmail(
                                user.getEmail(),
                                "New login",
                                "New login to your account"
                        );
                        return ResponseEntity.ok(token);
                    })
                    .orElseGet(() -> {
                        log.error("User not found with username: {}", userAuthDto.getUsername());
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    });

        } catch (Exception e) {
            log.error("Authentication failed for username: {}", userAuthDto.getUsername(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Registers a new user.
     *
     * <p>This method checks if the user's password and confirmation password match, and if the username
     * is already taken. If everything is valid, a new user is created and saved in the database.
     * The method also assigns the "USER" role to the new user, if it exists in the database.</p>
     *
     * @param userRegistrationDto the registration details of the user (username, email, password, etc.).
     * @return a {@link ResponseEntity} indicating the success or failure of user registration.
     */
    public ResponseEntity<?> createNewUser(UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userService.findUserByUsername(userRegistrationDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userService.createUser(userRegistrationDto);
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            throw new IllegalStateException("Role USER not found in the database");
        }
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userEntityRepository.save(user);

        log.info("Created user with: \nusername: {} \nemail: {}", user.getUsername(), user.getEmail());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}