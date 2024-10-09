package com.nznext.geargrove.login.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAuthDto {
    private String username;
    private String password;
}
