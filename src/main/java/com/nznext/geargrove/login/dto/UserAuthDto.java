package com.nznext.geargrove.login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for encapsulating the user authentication credentials.
 *
 * <p>This class is used to transfer the user's username and password during the login process.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Getter} - Automatically generates a getter method for the {@code username} and {@code password} fields.</li>
 *   <li>{@code @Setter} - Automatically generates a setter method for the {@code username} and {@code password} fields.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor for all required fields (final fields), but in this case, since there are no final fields, it generates the default constructor.</li>
 * </ul>
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UserAuthDto {
    /** The username of the user trying to authenticate. */
    private String username;
    /** The password of the user trying to authenticate. */
    private String password;
}
