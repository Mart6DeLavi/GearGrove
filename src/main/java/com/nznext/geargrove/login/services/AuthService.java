package com.nznext.geargrove.login.services;

import com.nznext.geargrove.login.dtos.UserAuthDto;
import com.nznext.geargrove.login.dtos.UserRegistrationDto;
import com.nznext.geargrove.login.entities.Role;
import com.nznext.geargrove.login.entities.UserEntity;
import com.nznext.geargrove.messages.EmailSender;
import com.nznext.geargrove.login.repositories.RoleRepository;
import com.nznext.geargrove.login.repositories.UserEntityRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final EmailSender emailSender;

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
