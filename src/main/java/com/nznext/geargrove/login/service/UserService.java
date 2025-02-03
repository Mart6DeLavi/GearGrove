package com.nznext.geargrove.login.service;

import com.nznext.geargrove.login.dto.UserRegistrationDto;
import com.nznext.geargrove.login.entity.Role;
import com.nznext.geargrove.login.entity.UserEntity;
import com.nznext.geargrove.login.exception.CreateUserException;
import com.nznext.geargrove.login.exception.DeleteUserException;
import com.nznext.geargrove.login.exception.NoSuchRoleException;
import com.nznext.geargrove.login.exception.NoSuchUserException;
import com.nznext.geargrove.login.repository.RoleRepository;
import com.nznext.geargrove.login.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing user-related operations such as user creation, user deletion,
 * and user loading for authentication purposes.
 *
 * <p>This service implements the {@link UserDetailsService} interface, which is used to load
 * user details during the authentication process. It provides methods to create new users,
 * find users by username, and delete users from the system.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Service} - Marks this class as a Spring service, making it eligible for dependency injection.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required fields (final fields),
 *   enabling dependency injection of the required services.</li>
 *   <li>{@code @Slf4j} - Provides logging functionality for this service.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    /** The repository for accessing user entities in the database. */
    private final UserEntityRepository userEntityRepository;

    /** The password encoder for securely encoding passwords. */
    private final BCryptPasswordEncoder passwordEncoder;

    /** The repository for accessing roles in the database. */
    private final RoleRepository roleRepository;

    /**
     * Finds a user by their username.
     *
     * <p>This method retrieves a {@link UserEntity} by its username from the repository.
     * If no user is found, it throws a {@link NoSuchUserException}.</p>
     *
     * @param username the username of the user to find.
     * @return an {@link Optional} containing the {@link UserEntity} if found.
     * @throws NoSuchUserException if no user is found with the given username.
     */
    public Optional<UserEntity> findUserByUsername(String username) {
        return Optional.ofNullable(userEntityRepository.findUserEntityByUsername(username))
                .orElseThrow(() -> new NoSuchUserException("No such user: " + username));
    }

    /**
     * Creates a new user.
     *
     * <p>This method creates a new user entity by setting the provided data, encoding the user's password,
     * assigning the "USER" role, and saving the user to the database. If there is an error during user creation,
     * it throws a {@link CreateUserException}.</p>
     *
     * @param userRegistrationDto the data for the user to be created.
     * @return the created {@link UserEntity}.
     * @throws CreateUserException if user creation fails.
     */
    public UserEntity createUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = new UserEntity();
        try {
            user.setUsername(userRegistrationDto.getUsername());
            user.setEmail(userRegistrationDto.getEmail());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setRegistrationDate(LocalDateTime.now());
            user.setAddress(userRegistrationDto.getAddress());

            Role userRole = roleRepository.findByName("USER");
            if (userRole == null) {
                throw new NoSuchRoleException("Role ROLE_USER not found in the database");
            }

            user.setRoles(Collections.singleton(userRole));
            userEntityRepository.save(user);
            log.info("User created successfully");
        } catch (Exception ex) {
            log.error("User creation failed", ex);
            throw new CreateUserException("Could not create user", ex);
        }
        return user;
    }

    /**
     * Loads a user by their username for authentication.
     *
     * <p>This method retrieves a {@link UserEntity} from the database by its username, converts it to a
     * {@link UserDetails} object with roles as authorities, and returns it for authentication. If no user is found,
     * a {@link UsernameNotFoundException} is thrown.</p>
     *
     * @param username the username of the user to load.
     * @return the {@link UserDetails} for the user.
     * @throws UsernameNotFoundException if no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository.findUserEntityByUsername(username)
                .map(person -> {
                    log.info("User loaded successfully");
                    return new User(
                            person.getUsername(),
                            person.getPassword(),
                            person.getRoles().stream()
                                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                                    .collect(Collectors.toList())
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    /**
     * Deletes a user by their user ID.
     *
     * <p>This method deletes a user from the database by their ID. If the deletion fails, it throws a
     * {@link DeleteUserException}.</p>
     *
     * @param userId the ID of the user to delete.
     * @throws DeleteUserException if user deletion fails.
     */
    public void deleteUser(long userId) {
        try {
            userEntityRepository.deleteById(userId);
            log.info("User deleted successfully");
        } catch (Exception ex) {
            log.error("User deletion failed", ex);
            throw new DeleteUserException("Could not delete user", ex);
        }
    }
}