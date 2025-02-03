package com.nznext.geargrove.products.exception;

/**
 * Custom exception for cases where a product is not found in the GearGrove application.
 *
 * <p>This exception is thrown when a requested product cannot be found in the system. It extends
 * {@link RuntimeException} and provides a constructor for passing a custom error message.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @NoArgsConstructor} - Generates a constructor with no arguments.</li>
 * </ul>
 */
public class NoSuchProductException extends RuntimeException {

    /**
     * Constructs a new {@link NoSuchProductException} with the specified detail message.
     *
     * <p>This constructor allows setting a custom error message to provide more information about the exception.</p>
     *
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public NoSuchProductException(String message) {
        super(message);
    }
}
