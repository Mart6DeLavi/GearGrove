package com.nznext.geargrove.login.exception;

/**
 * Custom exception for errors related to missing users in the GearGrove application.
 *
 * <p>This exception is thrown when a specified user cannot be found in the system.</p>
 *
 * <p>It extends {@link RuntimeException} and provides a constructor that accepts an error message.</p>
 */
public class NoSuchUserException extends RuntimeException {
    /**
     * Constructs a new {@link NoSuchUserException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public NoSuchUserException(String message) {
        super(message);
    }
}