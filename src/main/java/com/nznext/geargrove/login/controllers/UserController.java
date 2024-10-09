package com.nznext.geargrove.login.controllers;

import com.nznext.geargrove.login.dtos.UserAuthDto;
import com.nznext.geargrove.login.dtos.UserRegistrationDto;
import com.nznext.geargrove.login.services.AuthService;
import com.nznext.geargrove.login.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDto user) {
        return authService.createNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final UserAuthDto user) {
        return authService.createAuthToken(user);
    }

    @DeleteMapping("/delete{userId}")
    public void deleteUser(@PathVariable final Long userId) {
        userService.deleteUser(userId);
    }
}
