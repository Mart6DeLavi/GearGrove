package com.nznext.geargrove.login.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Data Transfer Object (DTO) for encapsulating the user registration details.
 *
 * <p>This class is used to transfer the data required for user registration, including
 * the username, email, password, confirmation password, and address.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Getter} - Automatically generates getter methods for the fields.</li>
 *   <li>{@code @Setter} - Automatically generates setter methods for the fields.</li>
 *   <li>{@code @RequiredArgsConstructor} - Generates a constructor with all required arguments for the fields.</li>
 *   <li>{@code @FieldDefaults} - Specifies the default access level for the fields, in this case {@code PRIVATE}.</li>
 * </ul>
 */
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationDto {
    /** The username chosen by the user for registration. */
    String username;

    /** The email address provided by the user for registration. */
    String email;

    /** The password chosen by the user for registration. */
    String password;

    /** The password confirmation to verify that the user entered the correct password. */
    String confirmPassword;

    /** The address provided by the user during registration. */
    String address;
}
