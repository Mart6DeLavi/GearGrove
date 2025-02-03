package com.nznext.geargrove.login.exception;

/**
 * Custom exception for errors encountered during user deletion in the GearGrove application.
 *
 * <p>This exception is thrown when there is an error during the process of deleting a user.</p>
 *
 * <p>It extends {@link RuntimeException} and provides constructors for passing an error message
 * and an underlying cause for the exception.</p>
 */
public class DeleteUserException extends RuntimeException {
    /**
     * Constructs a new {@link DeleteUserException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     * @param cause the cause of the exception (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     */
    public DeleteUserException(String message, Throwable cause) {
        super(message, cause);
    }
}