package com.nznext.geargrove.login.services;

import com.nznext.geargrove.login.dtos.UserRegistrationDto;
import com.nznext.geargrove.login.entities.Role;
import com.nznext.geargrove.login.entities.UserEntity;
import com.nznext.geargrove.login.exception.CreateUserException;
import com.nznext.geargrove.login.exception.DeleteUserException;
import com.nznext.geargrove.login.exception.NoSuchRoleException;
import com.nznext.geargrove.login.exception.NoSuchUserException;
import com.nznext.geargrove.login.repositories.RoleRepository;
import com.nznext.geargrove.login.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Optional<UserEntity> findUserByUsername(String username) {
        return Optional.of(userEntityRepository.findUserEntityByUsername(username))
                .orElseThrow( () -> new NoSuchUserException("No such user: " + username));
    }

    public UserEntity createUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = new UserEntity();
        try {
            user.setUsername(userRegistrationDto.getUsername());
            user.setEmail(userRegistrationDto.getEmail());
            user.setName(userRegistrationDto.getName());
            user.setSurname(userRegistrationDto.getSurname());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setRegistrationDate(userRegistrationDto.getRegistrationDate());
            user.setAddress(userRegistrationDto.getAddress());

            Role userRole = roleRepository.findByName("ROLE_USER");
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
                                    .toList()
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

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