package com.nznext.geargrove.orders.exception;

/**
 * Custom exception for handling cases when a user must be authenticated.
 *
 * <p>This exception is thrown when an operation requires the user to be authenticated, but the user is not authenticated.</p>
 *
 * <p>It extends {@link RuntimeException} and provides a constructor to pass a custom error message.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @NoArgsConstructor} - Generates a constructor with no arguments.</li>
 * </ul>
 */
public class UserMustBeAuthenticatedException extends RuntimeException {

    /**
     * Constructs a new {@link UserMustBeAuthenticatedException} with the specified detail message.
     *
     * <p>This constructor allows setting a custom error message to provide more information about the exception.</p>
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public UserMustBeAuthenticatedException(String message) {
        super(message);
    }
}
