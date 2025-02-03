package com.nznext.geargrove.login.exception;

/**
 * Custom exception for errors related to missing roles in the GearGrove application.
 *
 * <p>This exception is thrown when a specified role cannot be found in the system.</p>
 *
 * <p>It extends {@link RuntimeException} and provides a constructor that accepts an error message.</p>
 */
public class NoSuchRoleException extends RuntimeException {
    /**
     * Constructs a new {@link NoSuchRoleException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public NoSuchRoleException(String message) {
        super(message);
    }
}
