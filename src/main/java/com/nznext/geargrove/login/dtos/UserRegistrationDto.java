package com.nznext.geargrove.login.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDto {
    String username;
    String email;
    String password;
    String confirmPassword;
    String address;
}
